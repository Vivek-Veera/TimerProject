package com.example.timer;

// HomeActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // Navigate to Timer Screen
    public void openTimerScreen(View view) {
        startActivity(new Intent(this, TimerActivity.class));
    }

    // Navigate to Sound Settings
    public void openSoundSettings(View view) {
        startActivity(new Intent(this, SoundSettingsActivity.class));
    }

    // Navigate to Timer History
    public void openTimerHistory(View view) {
        startActivity(new Intent(this, TimerHistoryActivity.class));
    }
}
