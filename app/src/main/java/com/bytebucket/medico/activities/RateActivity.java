package com.bytebucket.medico.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bytebucket.medico.R;
import com.bytebucket.medico.modals.Appointment;
import com.bytebucket.medico.modals.Doctor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class RateActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button sRate;
    Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        ratingBar = findViewById(R.id.rating_bar);
        sRate = findViewById(R.id.rate_submit);

        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("appointment");
        appointment = gson.fromJson(strObj,Appointment.class);

        sRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appointment.setRate(true);
                //rate doc side
                DatabaseReference adRef = FirebaseDatabase.getInstance().getReference("appointments").child("doctor").child(appointment.getDfuid()).child(appointment.getDate()).child(appointment.getAppointmentId());
                adRef.setValue(appointment);
                //rate patient side
                DatabaseReference apRef = FirebaseDatabase.getInstance().getReference("appointments").child("patients").child(appointment.getPfuid()).child(appointment.getAppointmentId());
                apRef.setValue(appointment);
                //add to doc
                final DatabaseReference docRef = FirebaseDatabase.getInstance().getReference("doctors").child(appointment.getDfuid());
                docRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Doctor doctor = dataSnapshot.getValue(Doctor.class);
                        doctor.setRating(doctor.getRating()+ (int)(ratingBar.getRating()));
                        doctor.setCount(doctor.getCount()+1);
                        docRef.setValue(doctor);
                        Toast.makeText(RateActivity.this, "Rating done", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
