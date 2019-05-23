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
            foregroundIntent.setAction(ForeGroundService.ACTION_START_FOREGROUND_SERVICE);
            startService(foregroundIntent)
        }
        bt_stop_foreground.setOnClickListener {
            foregroundIntent.setAction(ForeGroundService.ACTION_STOP_FOREGROUND_SERVICE);
            startService(foregroundIntent)
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
//
//    @TargetApi(Build.VERSION_CODES.O)
//    private fun moveToStartedState() {
//        val intent = MyIntentBuilder(this)
//            .setCommand(Command.START).build()
//        if (isPreAndroidO()) {
//            Log.d(FragmentActivity.TAG, "Running on Android N or lower")
//            startService(intent)
//        } else {
//            Log.d(FragmentActivity.TAG, "Running on Android O")
//            startForegroundService(intent)
//        }
//    }
}
