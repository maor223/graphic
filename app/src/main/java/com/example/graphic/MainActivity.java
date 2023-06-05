package com.example.graphic;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    LinearLayout Ltowels, Lpillows, Lkeychain, Lcalender, Lmaaraz, Lbaby, Lother;
    private static final int REQUEST_CALL = 1;
    final String adress = "geo:32.165567,35.085866";
    int count = 0;
    FirebaseAuth mAuth;


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

        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        if (view == Ltowels) {
            intent.putExtra("name", "מגבות");
            startActivity(intent);
        } else if (view == Lpillows) {
            intent.putExtra("name", "כריות");
            startActivity(intent);
        } else if (view == Lkeychain) {
            intent.putExtra("name", "מחזיקי מפתחות");
            startActivity(intent);
        } else if (view == Lcalender) {
            intent.putExtra("name", "לוחות שנה");
            startActivity(intent);
        } else if (view == Lmaaraz) {
            intent.putExtra("name", "מארזים");
            startActivity(intent);
        } else if (view == Lbaby) {
            intent.putExtra("name", "תינוקות");
            startActivity(intent);
        } else if (view == Lother) {
            intent.putExtra("name", "עוד");
            startActivity(intent);
        }

    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (mAuth.getCurrentUser() == null) {
            getMenuInflater().inflate(R.menu.login_menu, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.maps) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(adress));
            Intent chooser = Intent.createChooser(intent, "Launch Map");
            startActivity(chooser);
        } else if (id == R.id.phone) {
            checkPhoneCall();
        } else if (id == R.id.sms) {
            openWhatsApp();
        } else if (id==R.id.order){
            startActivity (new Intent(MainActivity.this, AllOrders.class));
        }else if (id == R.id.login) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else if (id == R.id.logout) {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        return true;
    }

    //checking if permission confirmed
    public void checkPhoneCall() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (count == 0) {
                count++;
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                if (count == 1)
                    showPermissionExplenation(REQUEST_CALL);
                else {
                    Toast.makeText(this, "אין לך הרשאה", Toast.LENGTH_SHORT);
                }

            }
        }
        // calling to the phoneNumber if their is permission
        else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + "52-11101111");
            intent.setData(data);
            startActivity(intent);
        }

    }

    //showing explain of the Alert
    public void showPermissionExplenation(final int permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (permission == REQUEST_CALL) {
            builder.setTitle("הרשאת פלאפון");
            builder.setMessage("אנא אשר הרשאה דרל הגדרות האפליקציה");
        }
        builder.setPositiveButton("אשר", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (permission == REQUEST_CALL) {
                    count = 5;
                    settingsPermission();
                }
            }
        });
        builder.setNegativeButton("מסרב", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                count = 1;
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    //go to setting applications
    public int settingsPermission() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        this.startActivity(intent);
        checkPhoneCall();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            return count = 1;
        return count = 0;
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
    //opening whatsapp
    public void openWhatsApp(){
        try {
            String text = "היי הייתי רוצה לקבל פרטים נוספים לגבי המוצר שהזמנתי";// Replace with your message.

            String toNumber = "972545561643"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
            //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+toNumber +"&text="+text));
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}