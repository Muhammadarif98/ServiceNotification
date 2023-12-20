package com.example.servicenotification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import java.util.logging.Handler

class MyService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            sendNotification()
        return START_NOT_STICKY
    }

    @SuppressLint("ServiceCast")
     fun sendNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = ""
        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Уведомление")
            .setContentText("Прошло ВРЕМЯ после нажатия кнопки")
            .setSmallIcon(R.drawable.ic_notification)
            .build()

        val serviceIntent = Intent(applicationContext, MyService::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "My Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            //applicationContext.startForegroundService(serviceIntent)
            notificationManager.createNotificationChannel(channel)
        }
//        else{
//            (applicationContext as MyService).sendNotification()
//        }

        startForeground(1, notification)

    }

    override fun onBind(intent: Intent): IBinder? {
        return null

    }
}
class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context, MyService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }
}
//class MyBroadcastReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        val serviceIntent = Intent(context, MyService::class.java)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService(serviceIntent)
//        } else {
//            context.startService(serviceIntent)
//        }
//        (context as MyService).sendNotification()
//    }
//}
