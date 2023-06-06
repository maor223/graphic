package com.example.graphic;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    Calendar calendar;
    public static final String channel1ID = "channel1ID";
    public final static String defaultNotificationChannelID = "default" ;

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
                    /*Intent resultIntent = new Intent(OrderForm.this, MainActivity.class);
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
                    managerCompat.notify(1, builder.build());*/

                    Order order = new Order(tv1.getText().toString(), Integer.parseInt(etAmount.getText().toString()), etText.getText().toString()
                            , etClientName.getText().toString(), etPhoneNumber.getText().toString());

                    myRef.setValue(order);
                    addFragment();
                }
            }
        }
        if (view==btnArrivalTime){
            calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,new SetDate(),year,month,day);
            datePickerDialog.show();
        }


    }
    public  class SetDate implements DatePickerDialog.OnDateSetListener
    {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String str = "You selected :" + dayOfMonth + "/" + (monthOfYear+1) +"/" + year;
            Toast.makeText(OrderForm.this,str,Toast.LENGTH_LONG).show();

            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.clear();

            calendar .set(Calendar. YEAR , year) ;
            calendar .set(Calendar. MONTH , monthOfYear) ;
            calendar .set(Calendar. DAY_OF_MONTH , dayOfMonth) ;
            updateLabel() ;
        }
    }
    private void updateLabel () {
        String myFormat = "dd/MM/yy" ; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat , Locale. getDefault ()) ;
        Date date = calendar .getTime();
        btnArrivalTime .setText(sdf.format(date)) ;
        scheduleNotification(getNotification( btnArrivalTime .getText().toString()) , date.getTime()) ;
    }
    private void scheduleNotification (Notification notification , long delay) {
        Date currentDate = Calendar.getInstance().getTime();

        Long parrentDayTime = currentDate.getTime();

        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP , SystemClock.elapsedRealtime()+(delay - currentDate.getTime()), pendingIntent) ;
    }

    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, defaultNotificationChannelID ) ;
        builder.setContentTitle( "הזמנה בוצעה" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( channel1ID ) ;
        return builder.build() ;
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


    /*private void setAlarm(long timeInMillis) {
        Toast.makeText(this, "alarm seted", Toast.LENGTH_SHORT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class); // Create a broadcast receiver to handle the alarm
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);
    }*/
    /*public void sendOnChannel1(){
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification();
        mNotificationHelper.getMAnager().notify(1, nb.build());
    }*/

}