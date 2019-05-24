package com.example.services

import android.content.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, BackgroundAndBoundService::class.java)

        val foregroundIntent = Intent(this, ForeGroundService::class.java)

        val intentServiceIntent = Intent(this, MyIntentService::class.java)

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

        bt_start_intent_service.setOnClickListener {
            startService(intentServiceIntent)
        }


        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mMessageReceiver, IntentFilter(MyIntentService.MY_INTENT_SERVICE))

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

    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Get extra data included in the Intent
            val message = intent.getStringExtra(MyIntentService.BROADCAST_MESSAGE)
            tv_intent_service.text = message
        }
    }

    override fun onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
        super.onDestroy()
    }

}
