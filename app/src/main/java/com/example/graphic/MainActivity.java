package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        if(v==Lshirts){
            OpenItemActivity(Lshirts);
        }
        if(v==Ltowels){
            Toast.makeText(this, "towel", Toast.LENGTH_SHORT).show();
        }
        if(v==Lpillows){
            Toast.makeText(this, "pillow", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    public void OpenItemActivity(LinearLayout L){
        Intent intent = new Intent(MainActivity.this, ItemSelectedActivity.class);
        if (L==Lshirts){
            intent.putExtra("shirt",R.drawable.ic_baseline_account_balance_24);
        }
        startActivity(intent);
    }
}