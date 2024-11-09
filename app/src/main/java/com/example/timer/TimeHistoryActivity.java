package com.example.timer;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class TimerHistoryActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_history);

        databaseHelper = new DatabaseHelper(this);

        ListView listView = findViewById(R.id.list_view_history);
        Cursor cursor = databaseHelper.getTimerHistory();

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item_timer_history,
                cursor,
                new String[] { "duration", "endTime" },
                new int[] { R.id.text_duration, R.id.text_end_time },
                0
        );

        listView.setAdapter(adapter);
    }
}
