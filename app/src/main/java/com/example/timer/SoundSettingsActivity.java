package com.example.timer;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class SoundSettingsActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings);

        radioGroup = findViewById(R.id.radioGroupSounds);
        loadSavedSound();

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            if (checkedId == R.id.radioSound1) {
                mediaPlayer = MediaPlayer.create(this, R.raw.sound1);
            } else if (checkedId == R.id.radioSound2) {
                mediaPlayer = MediaPlayer.create(this, R.raw.sound2);
            } else if (checkedId == R.id.radioSound3) {
                mediaPlayer = MediaPlayer.create(this, R.raw.sound3);
            }
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        });


        Button saveButton = findViewById(R.id.btn_save_sound);
        saveButton.setOnClickListener(this::saveSelectedSound);
    }

    private void loadSavedSound() {
        SharedPreferences prefs = getSharedPreferences("SoundPrefs", MODE_PRIVATE);
        int savedSoundId = prefs.getInt("selectedSound", R.id.radioSound1);
        radioGroup.check(savedSoundId);
    }

    private void saveSelectedSound(View view) {
        SharedPreferences prefs = getSharedPreferences("SoundPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("selectedSound", radioGroup.getCheckedRadioButtonId());
        editor.apply();
    }
}
