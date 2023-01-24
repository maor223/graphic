package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class ActivityBearWithShirt extends AppCompatActivity {

    ImageView iv1, iv2, iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bear_with_shirt);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            int imv1 = bundle.getInt("Bear1");
            iv1.setImageResource(imv1);
            int imv2 = bundle.getInt("Bear2");
            iv2.setImageResource(imv2);
            int imv3 = bundle.getInt("Bear3");
            iv3.setImageResource(imv3);
        }


    }
}