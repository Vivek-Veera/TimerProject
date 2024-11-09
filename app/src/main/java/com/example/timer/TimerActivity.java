package com.example.timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {
    private CountDownTimer countDownTimer;
    private boolean isRunning = false;
    private long timeInMillis;
    private TextView timerDisplay;
    private EditText inputHours, inputMinutes, inputSeconds;
    private Button startButton, pauseButton, resetButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerDisplay = findViewById(R.id.tv_timer_display);
        inputHours = findViewById(R.id.et_hours);
        inputMinutes = findViewById(R.id.et_minutes);
        inputSeconds = findViewById(R.id.et_seconds);
        startButton = findViewById(R.id.btn_start);
        pauseButton = findViewById(R.id.btn_pause);
        resetButton = findViewById(R.id.btn_reset);

        mediaPlayer = MediaPlayer.create(this, R.raw.default_sound);

        startButton.setOnClickListener(v -> startTimer());
        pauseButton.setOnClickListener(v -> pauseTimer());
        resetButton.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {
        int hours = Integer.parseInt(inputHours.getText().toString());
        int minutes = Integer.parseInt(inputMinutes.getText().toString());
        int seconds = Integer.parseInt(inputSeconds.getText().toString());

        timeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;

        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeInMillis = millisUntilFinished;
                int hours = (int) (timeInMillis / 3600000);
                int minutes = (int) ((timeInMillis % 3600000) / 60000);
                int seconds = (int) ((timeInMillis % 60000) / 1000);

                timerDisplay.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            }

            @Override
            public void onFinish() {
                timerDisplay.setText("00:00:00");
                playNotificationSound();
                Toast.makeText(TimerActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
            }
        }.start();
        isRunning = true;
    }

    private void pauseTimer() {
        if (isRunning) {
            countDownTimer.cancel();
            isRunning = false;
        }
    }

    private void resetTimer() {
        if (isRunning) {
            countDownTimer.cancel();
        }
        timerDisplay.setText("00:00:00");
        inputHours.setText("0");
        inputMinutes.setText("0");
        inputSeconds.setText("0");
        isRunning = false;
    }

    private void playNotificationSound() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
