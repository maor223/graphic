package com.example.graphic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ItemSelectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_selected);

        Bundle intent = getIntent().getExtras();
        if (intent!=null){
            int image = intent.getInt("shirt");
        }
    }
}