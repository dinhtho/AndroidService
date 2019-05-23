package com.example.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.services.MyService.MyBinder



class MyService : Service() {
    private val mBinder = MyBinder()

    private val TAG = "MyService";
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ");
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ");
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind: ");
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ");
    }

    inner class MyBinder : Binder() {
         val service: MyService
             get() = this@MyService
    }
}
