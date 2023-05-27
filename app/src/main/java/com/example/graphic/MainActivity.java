package com.example.graphic;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout Ltowels, Lpillows, Lkeychain, Lcalender, Lmaaraz, Lbaby, Lother;
    Dialog dialog;
    EditText etUsername, etPassword;
    Button btnLogin;
    private static final int REQUEST_CALL = 1;
    int count = 0;

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
        Lbaby = findViewById(R.id.babies);
        Lbaby.setOnClickListener(this);
        Lother = findViewById(R.id.others);
        Lother.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        if (view==Ltowels){
            intent.putExtra("name", "מגבות");
            startActivity(intent);
        }
        else if (view==Lpillows){
            intent.putExtra("name", "כריות");
            startActivity(intent);
        }
        else if (view==Lkeychain){
            intent.putExtra("name", "מחזיקי מפתחות");
            startActivity(intent);
        }
        else if (view==Lcalender){
            intent.putExtra("name", "לוחות שנה");
            startActivity(intent);
        }
        else if (view==Lmaaraz){
            intent.putExtra("name", "מארזים");
            startActivity(intent);
        }
        else if (view==Lbaby){
            intent.putExtra("name", "תינוקות");
            startActivity(intent);
        }
        else if (view==Lother){
            intent.putExtra("name", "עוד");
            startActivity(intent);
        }
    }

    //menu
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
        if (id==R.id.maps){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:32.165567,35.085866"));
            Intent chooser=Intent.createChooser(intent,"Launch Map");
            startActivity(chooser);
        }
        else if(id==R.id.call){
            checkPhoneCall();
        }
        else if(id==R.id.login){
            createLoginDialog();
        }
        else if (id==R.id.logout){

        }
        return true;
    }
    public void checkPhoneCall(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            //go to setting applications
                Toast.makeText(this, "Please allow write to storage permission setting", Toast.LENGTH_LONG);
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                this.startActivity(intent);

        }
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Toast.makeText(this, "גישה נדחתה", Toast.LENGTH_SHORT).show();
            }

        }
    }

    //dialog
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