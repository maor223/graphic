package com.example.graphic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout Ltowels, Lpillows, Lkeychain, Lcalender, Lmaaraz;
    Dialog dialog;
    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lkeychain = findViewById(R.id.keyChains);
        Lkeychain.setOnClickListener(this);
        Lcalender = findViewById(R.id.calendar);
        Lcalender.setOnClickListener(this);
        Lmaaraz = findViewById(R.id.maarazim);
        Lmaaraz.setOnClickListener(this);
        Ltowels = findViewById(R.id.towels);
        Ltowels.setOnClickListener(this);
        Lpillows = findViewById(R.id.pillows);
        Lpillows.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        if (view==Ltowels){
            intent.putExtra("name", "מגבות");
            startActivity(intent);
        }
        if (view==Lpillows){
            intent.putExtra("name", "כריות");
            startActivity(intent);
        }
        if (view==Lkeychain){
            intent.putExtra("name", "מחזיקי מפתחות");
            startActivity(intent);
        }
        if (view==Lcalender){
            intent.putExtra("name", "לוחות שנה");
            startActivity(intent);
        }
        if (view==Lmaaraz){
            intent.putExtra("name", "מארזים");
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id==R.id.login){
            createLoginDialog();
        }
        else if (id==R.id.logout){

        }
        return true;
    }
    public void createLoginDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.login_dialog);
        dialog.setTitle("Login");
        dialog.setCancelable(true);
        etUsername=(EditText)dialog.findViewById(R.id.etUserName);
        etPassword=(EditText)dialog.findViewById(R.id.etPassword);
        btnLogin=(Button)dialog.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        dialog.show();
    }
}