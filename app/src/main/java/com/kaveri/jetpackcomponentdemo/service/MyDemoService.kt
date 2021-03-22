package com.kaveri.jetpackcomponentdemo.service

import android.app.NotificationChannel
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.kaveri.jetpackcomponentdemo.Constants
import com.kaveri.jetpackcomponentdemo.R
import com.kaveri.jetpackcomponentdemo.service.`interface`.UploadRepo
import kotlinx.coroutines.*
import java.lang.Thread.sleep

class MyDemoService : MyBaseService() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        Log.d(TAG, "service onBind")
    }


    override fun onCreate() {
        super.onCreate()
        TAG = MyDemoService::class.java.name
        Log.d(TAG, "service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "service onStartCommand")
        val serviceStart = super.onStartCommand(intent, flags, startId)
        val notification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setContentTitle("Demo Service")
            .setContentText("Demo service is runnning")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        startForeground(DEMO_SERVICE_ID, notification)
        uploadData()
        return serviceStart
    }

    override fun onDestroy() {
        Log.d(TAG, "Service destroyed")
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.d(TAG, "service on TaskRemoved")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "Service on Unbind")
        return super.onUnbind(intent)
    }
}

const val DEMO_SERVICE_ID = 1
