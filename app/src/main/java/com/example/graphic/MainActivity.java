package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout Lshirts, Ltowels, Lpillows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lshirts = findViewById(R.id.shirts);
        Lshirts.setOnClickListener(this);
        Ltowels = findViewById(R.id.towels);
        Ltowels.setOnClickListener(this);
        Lpillows = findViewById(R.id.pillows);
        Lpillows.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, ItemSelectedActivity.class);
        switch (view.getId()){

            case R.id.shirts:
                intent.putExtra("name", R.id.tvShirts);
                startActivity(intent);

            case R.id.pillows:
                intent.putExtra("name", R.id.tvTowels);
                startActivity(intent);

            case R.id.towels:
                intent.putExtra("name", R.id.tvPillows);
                startActivity(intent);
        }
    }
}