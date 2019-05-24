package com.example.services

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, BackgroundAndBoundService::class.java)

        val foregroundIntent = Intent(this, ForeGroundService::class.java)

        bt_start.setOnClickListener { startService(intent) }
        bt_stop.setOnClickListener { stopService(intent) }
        bt_bind.setOnClickListener { bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE) }
        bt_unbind.setOnClickListener { unbindService(serviceConnection) }

        bt_start_foreground.setOnClickListener {
            startService(foregroundIntent)
        }
        bt_stop_foreground.setOnClickListener {
            stopService(foregroundIntent)
        }

    }

    private val TAG = "MainActivity";

    private lateinit var myService: BackgroundAndBoundService

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected: " + p0.toString());
        }

        override fun onServiceConnected(p0: ComponentName?, binder: IBinder?) {
            myService = (binder as BackgroundAndBoundService.MyBinder).service
            Log.d(TAG, "onServiceConnected: ");
        }
    }
//    private fun startInForeground() {
//        val notificationIntent = Intent(this, ForeGroundService::class.java)
//        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
//        val builder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
//            .setSmallIcon(R.drawable.shsl_notification)
//            .setContentTitle("TEST")
//            .setContentText("HELLO")
//            .setTicker("TICKER")
//            .setContentIntent(pendingIntent)
//        val notification = builder.build()
//        if (Build.VERSION.SDK_INT >= 26) {
//            val channel = NotificationChannel(
//                NOTIFICATION_CHANNEL_ID,
//                NOTIFICATION_CHANNEL_NAME,
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            channel.description = NOTIFICATION_CHANNEL_DESC
//            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//        startForeground(NOTIFICATION_ID, notification)
//    }
}
