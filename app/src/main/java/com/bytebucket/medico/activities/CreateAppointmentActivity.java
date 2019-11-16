package com.bytebucket.medico.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bytebucket.medico.R;
import com.bytebucket.medico.modals.Appointment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAppointmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText etProblem, etDate;
    Button bCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle("Create an Appointment");
        }

        Bundle extras = getIntent().getExtras();
        final String dfuid = extras.getString("dfuid");

        init();

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(CreateAppointmentActivity.this, CreateAppointmentActivity.this,
                        2013, 2, 18);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.show();
            }
        });

        bCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String problem = etProblem.getText().toString();
                String date = etDate.getText().toString();
                final String pfuid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("appointments");
                DatabaseReference docRef=dbRef.child("doctor").child(dfuid).child(date);
                final String appointmentId= docRef.push().getKey();
                final Appointment appointment = new Appointment(problem, date, dfuid, pfuid, appointmentId,"pending",false);
                docRef.child(appointmentId).setValue(appointment).addOnSuccessListener(CreateAppointmentActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DatabaseReference patRef = dbRef.child("patients").child(pfuid);
                        patRef.child(appointmentId).setValue(appointment).addOnSuccessListener(CreateAppointmentActivity.this, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(CreateAppointmentActivity.this, "Appointment Created Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateAppointmentActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                            }
                        });
                    }
                });



            }
        });

    }

    private void init() {
        etProblem = findViewById(R.id.create_appointment_problem);
        etDate = findViewById(R.id.create_appointment_date);
        bCreate = findViewById(R.id.create_create_appointment);
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM/yyyy", Locale.getDefault());
        // 0 indexed months
        month++;
        String dateStr = day + "-" + month + "-" + year;
        etDate.setText(dateStr);
    }
}

