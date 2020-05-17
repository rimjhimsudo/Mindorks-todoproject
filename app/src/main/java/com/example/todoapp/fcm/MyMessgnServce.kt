package com.example.todoapp.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.todoapp.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyMessgnServce : FirebaseMessagingService(){

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("LOGMSG0", message.from.toString())
        Log.d("LOGMSG", message.notification?.body.toString())
        Log.d("LOGMSG",message.data.toString())
        setupNotfctn(message.notification?.body)
    }

    private fun setupNotfctn(body: String?) {
        val channelId="TODO ID" //whwevr we are on android 9 above we need to create channel
        val ringtone=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notifctnBulder=NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("todo notes app")
                .setContentText(body)
                .setSound(ringtone)
        val notictnManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O){

            val channel=NotificationChannel(channelId,"todo-notes",NotificationManager.IMPORTANCE_HIGH)
            notictnManager.createNotificationChannel(channel)
        }
        notictnManager.notify(0,notifctnBulder.build())

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

}