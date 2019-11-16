package com.bytebucket.medico.utilities;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmService extends IntentService {

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        int h = intent.getIntExtra("hour", 0);
        int m = intent.getIntExtra("min", 0);
        int code = intent.getIntExtra("id",0);
        int freq = intent.getIntExtra("freq",24);
        String pillName = intent.getStringExtra("pillName");
        String pillDosage = intent.getStringExtra("pillDosage");
        calendar.set(Calendar.HOUR_OF_DAY, h);
        calendar.set(Calendar.MINUTE, m);
        Intent myIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        myIntent.putExtra("pillName",pillName);
        myIntent.putExtra("pillDosage",pillDosage);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), code, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        //alarmManager.setRepeating(AlarmManager.RTC,calendar.getTimeInMillis(),freq*60*60*1000,pendingIntent);
        //Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
    }

}
