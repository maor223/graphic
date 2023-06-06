package com.example.graphic;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class NotificationHelper extends ContextWrapper {

    public static final String channel1ID = "channel1ID";
    public static final String channel1NAME = "channel 1";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createChannel(){
            NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel1.enableLights(true);
            channel1.enableVibration(true);
            channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

            getMAnager().createNotificationChannel(channel1);
    }

    public NotificationManager getMAnager(){
        if (mManager==null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification(){
        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle("הזמנה בוצעה")
                .setContentText("תוכלו ליצור קשר בעזרת הודעת וואצאפ או בעזרת חיוג")
                .setAutoCancel(true)
                .setColor(Color.parseColor("" +
                        "#FF9800"));
    }
}
