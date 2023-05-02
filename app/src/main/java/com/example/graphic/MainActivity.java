package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        if (view==Lshirts){
            intent.putExtra("name", "חולצות");
            startActivity(intent);
        }
        if (view==Ltowels){
            intent.putExtra("name", "מגבות");
            startActivity(intent);
        }
        if (view==Lpillows){
            intent.putExtra("name", "כריות");
            startActivity(intent);
        }
    }
}