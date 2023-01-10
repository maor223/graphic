package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityBearWithShirt extends AppCompatActivity {

    ImageView bear1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bear_with_shirt);

        bear1 = findViewById(R.id.ivBear1);
        Intent intent = getIntent();
        String Bear1 = intent.getExtras().getString("Bear1");
        bear1.setImageResource(R.drawable.bear_with_shirt_one);
    }
}