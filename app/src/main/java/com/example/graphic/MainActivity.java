package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    LinearLayout Lshirts, Ltowels, Lpillows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lshirts = findViewById(R.id.shirts);
        Lshirts.setOnTouchListener(this);
        Ltowels = findViewById(R.id.towels);
        Ltowels.setOnTouchListener(this);
        Lpillows = findViewById(R.id.pillows);
        Lpillows.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent motionEvent) {
        return false;
    }
}