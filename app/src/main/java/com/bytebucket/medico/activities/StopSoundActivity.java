package com.bytebucket.medico.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bytebucket.medico.R;

public class StopSoundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_sound);
    }

    public void stop(View view) {
        System.gc();
        System.exit(0);
    }
}
