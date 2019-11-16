package com.bytebucket.medico.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bytebucket.medico.R;
import com.bytebucket.medico.adapters.DoctorAdapter;
import com.bytebucket.medico.modals.Doctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorsActivity extends AppCompatActivity {


    DatabaseReference dbref;
    private RecyclerView rvDoctorsList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Doctor> doctors = new ArrayList<Doctor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);


        init();

        rvDoctorsList.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        rvDoctorsList.setLayoutManager(layoutManager);

        mAdapter = new DoctorAdapter(doctors, DoctorsActivity.this);
        rvDoctorsList.setAdapter(mAdapter);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(DoctorsActivity.this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(DoctorsActivity.this, R.drawable.divider));
        rvDoctorsList.addItemDecoration(itemDecorator);

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                doctors.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Doctor doctor = ds.getValue(Doctor.class);
                    doctors.add(doctor);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void init() {

        rvDoctorsList = findViewById(R.id.doctors_list);
        dbref = FirebaseDatabase.getInstance().getReference("doctors");
    }
}
