package com.kaveri.jetpackcomponentdemo.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.kaveri.jetpackcomponentdemo.Constants
import kotlinx.coroutines.*

class DemoBoundService : MyBaseService() {

    private var coroutineScope = CoroutineScope(Dispatchers.Default)
    private var exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "exception occured : $throwable")
    }

    val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): DemoBoundService = this@DemoBoundService
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "service onBind")
        return binder
    }


    override fun onCreate() {
        super.onCreate()
        TAG = "DemoBoundService"
        Log.d(TAG, "service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "service onStartCommand")
       return  super.onStartCommand(intent, flags, startId)
    }

   /* override fun uploadData() {
        runBlocking {
            coroutineScope.launch {
                var i = 0
                while(i<10) {
                    Thread.sleep(2000L)
                    Log.d(TAG, "Data uploaded $i")
                    i++
                }
                stopSelf()
            }
        }
    }*/

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
