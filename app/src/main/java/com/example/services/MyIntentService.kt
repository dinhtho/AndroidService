package com.example.services

import android.app.IntentService
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v4.content.LocalBroadcastManager

/**
 * Created by tho nguyen on 2019-05-24.
 */
class MyIntentService : IntentService {

    constructor() : super("IntentService") {
        this.myIntent = Intent(MY_INTENT_SERVICE)
    }

    var myIntent: Intent

    companion object {
        const val BROADCAST_MESSAGE = "broadcastMessage"
        const val MY_INTENT_SERVICE = "MyIntentService"

    }

    override fun onHandleIntent(intent: Intent) {
        for (i in 0..20) {
            Thread.sleep(1000)
            LocalBroadcastManager.getInstance(applicationContext)
                .sendBroadcast(myIntent.putExtra(BROADCAST_MESSAGE, i.toString()))
        }
        LocalBroadcastManager.getInstance(applicationContext)
            .sendBroadcast(myIntent.putExtra(BROADCAST_MESSAGE, "completed"))
    }
}