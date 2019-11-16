package com.bytebucket.medico.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bytebucket.medico.R;
import com.bytebucket.medico.modals.Doctor;
import com.google.gson.Gson;

public class DoctorDetailsActivity extends AppCompatActivity {

    Button bCreateAppointment;
    TextView tvName, tvNumber, tvSpecility;
    de.hdodenhof.circleimageview.CircleImageView civProfileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        init();
        Bundle bundle = getIntent().getExtras();
        Gson gson = new Gson();
        String strObj = getIntent().getStringExtra("doctor");
        final Doctor doctor = gson.fromJson(strObj, Doctor.class);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle(doctor.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvName.setText(doctor.getName());
        tvNumber.setText(doctor.getMobile());
        tvSpecility.setText(doctor.getSpeciality());
        civProfileImage.setImageResource(bundle.getInt("image"));

        bCreateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAppointment = new Intent(DoctorDetailsActivity.this, CreateAppointmentActivity.class);
                createAppointment.putExtra("dfuid", doctor.getFuid());
                startActivity(createAppointment);
            }
        });
    }

    private void init() {
        bCreateAppointment = findViewById(R.id.doctor_details_take_appointment);
        tvName = findViewById(R.id.doctor_details_name);
        tvNumber =findViewById(R.id.doctor_details_phone);
        tvSpecility = findViewById(R.id.doctor_details_speciality);
        civProfileImage = findViewById(R.id.doctor_details_profile_image);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
