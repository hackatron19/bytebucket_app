package com.bytebucket.medico.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bytebucket.medico.R;
import com.bytebucket.medico.utilities.AlarmService;
import com.bytebucket.medico.utilities.Constants;

public class AddReminderActivity extends AppCompatActivity {

    TimePicker timePicker;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText pillName, pillDosage, pillFrequency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        if(getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle("Add Reminder");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        pillName = findViewById(R.id.pill_reminder_pill_name);
        pillDosage = findViewById(R.id.pill_reminder_pill_dosage);
        pillFrequency = findViewById(R.id.pill_reminder_pill_frequency);
        timePicker = findViewById(R.id.pill_reminder_tp);

        findViewById(R.id.pill_reminder_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = timePicker.getCurrentHour();
                int m = timePicker.getCurrentMinute();
                prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                editor = prefs.edit();
                int count = prefs.getInt("count", 0);
                count++;
                String pillNameText = pillName.getText().toString();
                if(pillNameText.equalsIgnoreCase(""))
                {
                    pillNameText = "Your Pill "+count;
                }
                String pillDosageText = pillDosage.getText().toString();

                //edit here
                if(Constants.DOSAGE < Integer.parseInt(pillDosageText))
                    Toast.makeText(AddReminderActivity.this, "You are taking more medicine than what is recommended", Toast.LENGTH_SHORT).show();


                if(pillDosageText.equalsIgnoreCase(""))
                {
                    pillDosageText = "Take only required pill dosage.";
                }
                String pillFrequencyText = pillFrequency.getText().toString();
                if(pillFrequencyText.equalsIgnoreCase(""))
                {
                    pillFrequencyText ="24";
                }
                int pillFrequencyNumber = Integer.parseInt(pillFrequencyText);
                editor.putInt("count",count);
                editor.putString("name"+count,pillNameText);
                editor.putString("dosage"+count,pillDosageText);
                editor.putInt("hour"+count,h);
                editor.putInt("min"+count,m);
                editor.putInt("freq"+count,pillFrequencyNumber);
                editor.putInt("id"+count,count);
                editor.apply();
                Intent intent = new Intent(AddReminderActivity.this, AlarmService.class);
                intent.putExtra("hour",h);
                intent.putExtra("min",m);
                intent.putExtra("id",count);
                intent.putExtra("freq",pillFrequencyNumber);
                intent.putExtra("pillName",pillNameText);
                intent.putExtra("pillDosage",pillDosageText);
                startService(intent);
                finish();
            }
        });
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
