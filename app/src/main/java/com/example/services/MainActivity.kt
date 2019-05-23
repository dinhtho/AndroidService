package com.example.services

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Build
import android.annotation.TargetApi
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
