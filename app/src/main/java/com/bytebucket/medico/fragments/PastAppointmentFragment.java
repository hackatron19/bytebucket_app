package com.bytebucket.medico.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bytebucket.medico.R;
import com.bytebucket.medico.adapters.AppointmentAdapter;
import com.bytebucket.medico.modals.Appointment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Collections.sort;

/**
 * A simple {@link Fragment} subclass.
 */
public class PastAppointmentFragment extends Fragment {

    RecyclerView recyclerView;
    RelativeLayout emptyRL;
    DatabaseReference dbRef;
    AppointmentAdapter appointmentAdapter;
    ArrayList<Appointment> appointments = new ArrayList<>();

    public PastAppointmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_appointment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.past_appointments_rv);
        emptyRL = view.findViewById(R.id.appointments_empty_layout);
        String pfuid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        final String appointmentDate = sdf.format(date.getTime());
        dbRef = FirebaseDatabase.getInstance().getReference("appointments").child("patients").child(pfuid);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        appointmentAdapter = new AppointmentAdapter(getActivity(),appointments);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(appointmentAdapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appointments.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Appointment appointment = ds.getValue(Appointment.class);
                    String date = appointment.getDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                    Date d1,d2;
                    try {
                        d1 = sdf.parse(date);
                        d2 = sdf.parse(appointmentDate);
                        long t1 = d1.getTime();
                        long t2 = d2.getTime();
                        if(t1<t2)
                            appointments.add(appointment);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if(appointments.size()==0)
                    emptyRL.setVisibility(View.VISIBLE);
                else
                    emptyRL.setVisibility(View.GONE);
                sort(appointments);
                appointmentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    void sort(List<Appointment> appointmentList) {
        //Comparator for date
        Collections.sort(appointmentList, new Comparator<Appointment>() {
            @Override
            public int compare(Appointment a1, Appointment a2) {
                String d1 = a1.getDate();
                String d2 = a2.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                long t1 = 0, t2 = 0;
                Date mDate1 = null, mDate2 = null;
                try {
                    mDate1 = sdf.parse(d1);
                    mDate2 = sdf.parse(d2);
                    t1 = mDate1.getTime();
                    t2 = mDate2.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (t1 < t2)
                    return 1;
                else if (t1 > t2)
                    return -1;
                else
                    return 0;
            }
        });
    }
}
