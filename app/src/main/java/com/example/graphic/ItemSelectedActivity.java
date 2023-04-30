package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemSelectedActivity extends AppCompatActivity {
    TextView tv1;
    ImageView iv1;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);
        tv1 = findViewById(R.id.tv1);
        iv1 = findViewById(R.id.iv1);

        bundle = getIntent().getExtras();

        if (bundle!=null){

            String name = bundle.getString("name");

            SetUp(name);
        }

    }
    public void SetUp(String name){
        if (name.equals("חולצות")){
            iv1.setImageResource(R.drawable.yellow_shirt);
        }
        else if (name.equals("כריות")){
            iv1.setImageResource(R.drawable.yellow_shirt);
        }
    }

}