package com.bytebucket.medico.utilities;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.bytebucket.medico.R;
import com.bytebucket.medico.activities.LoginActivity;
import com.bytebucket.medico.activities.StopSoundActivity;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {

    public static void displayNotification(Context context, String title, String body) {
        Intent intent = new Intent(context, StopSoundActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, LoginActivity.CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setOngoing(true)
                //.addAction(R.mipmap.ic_launcher_round, "STOP", pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, mBuilder.build());
    }

}
