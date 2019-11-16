package com.bytebucket.medico.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytebucket.medico.R;
import com.bytebucket.medico.modals.Appointment;
import com.bytebucket.medico.modals.Doctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {


    Context context;
    ArrayList<Appointment> appointments;

    public AppointmentAdapter(Context context, ArrayList<Appointment> appointments) {
        this.context = context;
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_appointment, parent, false);
        return new AppointmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AppointmentViewHolder holder, int position) {
        final Appointment appointment = appointments.get(position);
        holder.tvProblem.setText(appointment.getProblem());

        // Fetch and Parse the date
        String dateStr = appointment.getDate();
        DateFormat dfMonthYear = new SimpleDateFormat("MMM yyyy");
        DateFormat dfDate = new SimpleDateFormat("dd");
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String nameMonth = dfMonthYear.format(date);
        String nameDate = dfDate.format(date);

        holder.tvMonthYear.setText(nameMonth);
        holder.tvDate.setText(nameDate);
        final Doctor[] doctor = new Doctor[1];

        // Fetch the details of the doctor
        DatabaseReference dbrefDoc = FirebaseDatabase.getInstance().getReference("doctors").child(appointment.getDfuid());
        dbrefDoc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doctor[0] = dataSnapshot.getValue(Doctor.class);
                holder.tvDocName.setText(doctor[0].getName());
                holder.tvSpeciality.setText(doctor[0].getSpeciality());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        holder.tvStatus.setText(appointment.getStatus());
        changeColor(context, holder.tvStatus, appointment.getStatus());

        //ability to delete appointments
        if(appointment.getStatus().equalsIgnoreCase("accepted"))
            holder.ivDelete.setVisibility(View.GONE);
        else
            holder.ivDelete.setVisibility(View.VISIBLE);
        //delete when status is not accepted
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //delete doc side
                    DatabaseReference adRef = FirebaseDatabase.getInstance().getReference("appointments").child("doctor").child(appointment.getDfuid()).child(appointment.getDate()).child(appointment.getAppointmentId());
                    adRef.setValue(null);
                    //delete patient side
                    DatabaseReference apRef = FirebaseDatabase.getInstance().getReference("appointments").child("patients").child(appointment.getPfuid()).child(appointment.getAppointmentId());
                    apRef.setValue(null);
                }
                catch (Exception e)
                {
                    //exception handling
                }
            }
        });

    }

    private void changeColor(Context context, TextView tvStatus, String status) {
        if(status.compareToIgnoreCase("rejected")==0){
            tvStatus.setTextColor(Color.parseColor("#d50000"));
        }else if(status.compareToIgnoreCase("accepted")==0){
            tvStatus.setTextColor(Color.parseColor("#00e676"));
        }else{
            tvStatus.setTextColor(Color.parseColor("#ffeb3b"));
        }
    }


    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvMonthYear, tvDocName, tvSpeciality, tvProblem, tvStatus;
        ImageView ivDelete;
        RelativeLayout rlDate;

        public AppointmentViewHolder(View v) {
            super(v);
            tvDate = v.findViewById(R.id.single_appointment_date);
            tvMonthYear = v.findViewById(R.id.single_appointment_month);
            tvDocName = v.findViewById(R.id.single_appointment_detail_docname);
            tvSpeciality = v.findViewById(R.id.single_appointment_detail_speciality);
            tvProblem = v.findViewById(R.id.single_appointment_detail_problem);
            tvStatus = v.findViewById(R.id.single_appointment_status);
            ivDelete = v.findViewById(R.id.single_appointment_delete);
            rlDate = v.findViewById(R.id.single_appointment_date_layout);

        }
    }
}


