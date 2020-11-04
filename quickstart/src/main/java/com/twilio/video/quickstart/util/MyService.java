package com.twilio.video.quickstart.util;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.twilio.video.quickstart.R;
import com.twilio.video.quickstart.activity.MainActivity;
import com.twilio.video.quickstart.activity.VideoActivity;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
public class MyService extends FirebaseMessagingService {

    PendingIntent pendingIntent;
    String postId,userId;

    public MyService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
Log.e("ddafdgdgd","jjggg");
     //   customProgress.hideProgress();
        Log.e("ajsfkj","dhkfjhf"+remoteMessage.getNotification().getTitle());
        Log.e("ajsfkjsjdj","dhkfjhf"+remoteMessage.getNotification().getBody());

     /*   Gson gson =  new Gson();
        String json = gson.toJson(remoteMessage);

        Log.e("ajsfkj","dhkfjhf"+json);
     */
        /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/

        Intent intent = new Intent("android.intent.category.LAUNCHER");
        intent.setClassName("com.twilio.video.qickstart", "com.twilio.video.qickstart.MainActivity");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

       // startActivity(new Intent(getApplicationContext(), MainActivity.class));

       // showNotification(remoteMessage);
    }

    public  void showNotification(RemoteMessage remoteMessage){


        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="com.twilio.video.qickstart";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,"notification",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("hello");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        if(!remoteMessage.getData().isEmpty()){

           /* postId=remoteMessage.getData().get("postID");
            postTaskController.setPostId(postId);
            userId=remoteMessage.getData().get("userID");
            postTaskController.setUserID(userId);
*/
            Log.e("jkdbfjkb","asfnj"+remoteMessage.getData());



// Not worked in below pie versions
    Gson gson = new Gson();
    String json = gson.toJson(remoteMessage.getData());

    Log.e("sfjfn","jfkfbkj"+json);

    Log.e("jkdbfjkb","asfnj"+remoteMessage.getData());

    Gson gson1=new Gson();
    String json1=gson1.toJson(remoteMessage.getNotification());

    Log.e("sfjfn","jfkfbkj"+json1);


}
        Intent intent1 = new Intent(this, VideoActivity.class);
        pendingIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);



/*
        if(!remoteMessage.getData().get("type").isEmpty()) {

            if(remoteMessage.getData().get("type").equals("PostJob")) {

                Log.e("gy","hgfjh");

                Intent intent = new Intent(this, MyBrowseTaskDetails.class);
                pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            } else if (remoteMessage.getData().get("type").equals("Booking")) {

                Log.e("sjg","jkhbf");

                BOOKING_ID=remoteMessage.getData().get("bookingID");


                if(remoteMessage.getData().get("completedTo").equals("Poster")) {

                    Intent intent = new Intent(this, BookingPosterDetailsActivity.class);
                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                }else{

                    Intent intent = new Intent(this, BookingTaskerDetailsActivity.class);
                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                }

            } else if (remoteMessage.getData().get("type").equals("WithDrawn")) {

                Log.e("sjg","jkhbf");

                BOOKING_ID=remoteMessage.getData().get("bookingID");


                if(remoteMessage.getData().get("withDrawnTo").equals("Poster")) {

                    Intent intent = new Intent(this, BookingPosterDetailsActivity.class);
                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                }else{

                    Intent intent = new Intent(this, BookingTaskerDetailsActivity.class);
                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                }

            }else {


                if(postTaskController.getUserID().equals(NavigationActivity.userId)) {


                    Intent intent = new Intent(this, MytasksDetails.class);

                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                }else {
                    Intent intent = new Intent(this, MyBrowseTaskDetails.class);

                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                }
            }


        } else{


            Log.e("jksdhfjbk","jhjf");


                if(postTaskController.getUserID().equals(NavigationActivity.userId)) {


                    Intent intent = new Intent(this, MytasksDetails.class);

                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                }else {

                    Intent intent = new Intent(this, MyBrowseTaskDetails.class);
                    pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                }
        }
*/

        NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);
//Changed
 notificationBuilder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).
                setSmallIcon(R.mipmap.ic_launcher).setContentTitle(Objects.requireNonNull(remoteMessage.getNotification()).getTitle()).setContentText(remoteMessage.getNotification().getBody())
                .setContentInfo("Info").setPriority(NotificationManager.IMPORTANCE_HIGH).setContentIntent(pendingIntent);

//Changed

        SessionManager sessionManager = new SessionManager(MyService.this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            if(sessionManager.isLoggedIn()){

            Log.e("djbd","djnjf");
            notificationBuilder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).
                    setSmallIcon(R.mipmap.ic_launcher).setContentTitle(remoteMessage.getNotification().getTitle()).setContentText(remoteMessage.getNotification().getBody())
                    .setContentInfo("Info").setPriority(NotificationManager.IMPORTANCE_HIGH).setContentIntent(pendingIntent);
            }else{

                Log.e("djdffb", "djnsdjf");
                Intent intent = new Intent(this, VideoActivity.class);
                pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                notificationBuilder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).
                        setSmallIcon(R.mipmap.ic_launcher).setContentTitle(remoteMessage.getNotification().getTitle()).setContentText(remoteMessage.getNotification().getBody())
                        .setContentInfo("Info").setPriority(NotificationManager.IMPORTANCE_HIGH).setContentIntent(pendingIntent);

            }
        }else{

            if(sessionManager.isLoggedIn()) {

                Log.e("djbdd", "djnsdjf");
                notificationBuilder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).
                        setSmallIcon(R.mipmap.ic_launcher).setContentTitle(remoteMessage.getNotification().getTitle()).setContentText(remoteMessage.getNotification().getBody())
                        .setContentInfo("Info").setPriority(NotificationCompat.PRIORITY_HIGH).setContentIntent(pendingIntent);
            }else {
                Log.e("djbffdg", "djnsdjf");

                Intent intent = new Intent(this, VideoActivity.class);
                pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                notificationBuilder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL).setWhen(System.currentTimeMillis()).
                        setSmallIcon(R.mipmap.ic_launcher).setContentTitle(remoteMessage.getNotification().getTitle()).setContentText(remoteMessage.getNotification().getBody())
                        .setContentInfo("Info").setPriority(NotificationManager.IMPORTANCE_HIGH).setContentIntent(pendingIntent);

            }

        }


        notificationManager.notify(new Random().nextInt(),notificationBuilder.build());

    }


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("tokenfirebase",s);
    }

}
