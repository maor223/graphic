package com.example.graphic;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graphic.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Calendar;

public class OrderForm extends AppCompatActivity implements View.OnClickListener {
    TextView tv1;
    EditText etAmount, etText, etClientName, etPhoneNumber;
    Button btnConfirmOrder;
    Bundle bundle;
    String productName;
    Button btnArrivalTime;

    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    FirebaseAuth mAuth;

    NotificationHelper mNotificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        frameLayout = findViewById(R.id.frameLayout);
        fragmentManager = getSupportFragmentManager();

        btnArrivalTime = findViewById(R.id.btnArrivalTime);
        btnArrivalTime.setOnClickListener(this);
        tv1 = findViewById(R.id.tv1);
        etAmount = findViewById(R.id.etAmount);
        etText = findViewById(R.id.etText);
        etClientName = findViewById(R.id.etClientName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnConfirmOrder = findViewById(R.id.btnConfirmOrder);
        btnConfirmOrder.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        bundle = getIntent().getExtras();
        productName = bundle.getString("productName");
        tv1.setText(productName);

    }

    @Override
    protected void onStart() {
        super.onStart();
        frameLayout.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("orders/"+mAuth.getCurrentUser().getUid());
        if (view==btnConfirmOrder){
            if(mAuth.getCurrentUser()!=null){
                if (TextUtils.isEmpty(etClientName.getText().toString())&&TextUtils.isEmpty(etPhoneNumber.getText().toString())){
                    etClientName.setError("שדה חובה");
                    etClientName.requestFocus();
                }
                else if (etPhoneNumber.getText().toString().length()!=10){
                    etPhoneNumber.setError("מספר פלאפון חייב להיות מעל עשרה ספרות");
                    etPhoneNumber.requestFocus();
                }

                else{
                    Intent resultIntent = new Intent(OrderForm.this, MainActivity.class);
                    PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                        NotificationChannel channel = new NotificationChannel("myCh", "My Channel", NotificationManager.IMPORTANCE_DEFAULT);

                        NotificationManager manager = getSystemService(NotificationManager.class);
                        manager.createNotificationChannel(channel);
                    }

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "myCh")
                            .setSmallIcon(R.drawable.icon)
                            .setContentTitle("הזמנה בוצעה")
                            .setContentText("תוכלו ליצור קשר בעזרת הודעת וואצאפ או בעזרת חיוג")
                            .setAutoCancel(true)
                            .setColor(Color.parseColor("" +
                                    "#FF9800"))
                            .setContentIntent(resultPendingIntent);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
                    managerCompat.notify(1, builder.build());


                    Order order = new Order(tv1.getText().toString(), Integer.parseInt(etAmount.getText().toString()), etText.getText().toString()
                            , etClientName.getText().toString(), etPhoneNumber.getText().toString());

                    myRef.setValue(order);
                    addFragment();
                }
            }
        }


    }
    public void sendOnChannel1(){
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification();
        mNotificationHelper.getMAnager().notify(1, nb.build());
    }

    public void addFragment(){
        frameLayout.setVisibility(View.VISIBLE);
        OrderFragment orderFragment = (OrderFragment)fragmentManager.findFragmentByTag("order");
        if (orderFragment==null){
            orderFragment = new OrderFragment();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, orderFragment, "order");
            fragmentTransaction.commit();
        }
    }
}