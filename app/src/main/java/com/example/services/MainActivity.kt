package com.example.services

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.annotation.TargetApi
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.os.IBinder
import android.support.v4.app.FragmentActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, MyService::class.java)
        bt_start.setOnClickListener { startService(intent) }
        bt_stop.setOnClickListener { stopService(intent) }
        bt_bind.setOnClickListener { bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE) }
        bt_unbind.setOnClickListener { unbindService(serviceConnection) }
    }

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
