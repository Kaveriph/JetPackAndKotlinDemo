package com.kaveri.jetpackcomponentdemo.DemoApplication

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.kaveri.jetpackcomponentdemo.Constants
import com.kaveri.jetpackcomponentdemo.di.demoAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DempApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DempApplication)
        modules(listOf(demoAppModule))}
        createNotificationChannels(this)
    }

    fun createNotificationChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var importance = NotificationManager.IMPORTANCE_HIGH
            var channel = NotificationChannel(Constants.CHANNEL_ID, "demo service", importance).apply {
                description = "Demo service notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}