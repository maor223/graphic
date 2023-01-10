package com.example.graphic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnTouchListener {

    ImageView bear1, shirt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bear1 = findViewById(R.id.ivBear);
        bear1.setOnTouchListener(this);

        shirt1 = findViewById(R.id.ivShirt);
        shirt1.setOnTouchListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                openActivity1();
        }
        return super.onOptionsItemSelected(item);
    }

    public void openActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        float f = (float)0.8;

        if(view==bear1){
            if (event.getAction()!=MotionEvent.ACTION_MOVE) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    view.setAlpha(f);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1);
                    Intent intent = new Intent(this, ActivityBearWithShirt.class);
                    intent.putExtra("Bear1",R.id.ivBear);
                    startActivity(intent);
                }
            }
        }
        else{
            if (event.getAction()!=MotionEvent.ACTION_MOVE) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    view.setAlpha(f);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    view.setAlpha(1);
                    Intent intent = new Intent(this, ActivityBearWithShirt.class);
                    startActivity(intent);
                }
            }
        }

        return true;
    }
}