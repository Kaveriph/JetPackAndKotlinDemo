package com.kaveri.jetpackcomponentdemo.activities

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kaveri.jetpackcomponentdemo.R
import com.kaveri.jetpackcomponentdemo.navigators.MainNavigator
import com.kaveri.jetpackcomponentdemo.repositories.DemoRepository
import com.kaveri.jetpackcomponentdemo.service.DemoBoundService
import com.kaveri.jetpackcomponentdemo.service.MyDemoService
import com.kaveri.jetpackcomponentdemo.viewmodel.MainViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(),   MainNavigator {

    private lateinit var mBoundService : DemoBoundService
    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected")
            mBoundService = (service as DemoBoundService.LocalBinder).getService()
            mBoundService.uploadData()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected")
        }
    }

    private val TAG = MainActivity::class.java.name
    private val serviceIntent : Intent by lazy {
         Intent(
            this,
            MyDemoService::class.java
        )
    }
    private val boundServiceIntent : Intent by lazy {
        Intent(
            this,
            DemoBoundService::class.java
        )
    }
    private var service: MyDemoService? = null
    private val mainViewModel: MainViewModel<MainNavigator> by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.setNavigator(this)
        updateViewModelData()
        setListeners()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun readMessageFromService( message: String) {
        Log.d(TAG, "Update from service : $message")
        mainViewModel.messageFRomService.postValue(message)
        mainViewModel.saveDataLocally(message)
    }

    private fun updateViewModelData() {
        mainViewModel.screenName.value = "MainActivity"
    }

    private fun setListeners() {
        mainViewModel.screenName.observe(this,
        Observer {
            Log.d(this.componentName.className, "screen $it")
        })
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        EventBus.getDefault().register(this)
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun uploadData() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(
            serviceIntent
        )
        else startService(serviceIntent)
        mainViewModel.setContextWrapper(this)
    }

    override fun uploadDataViaBoundservice() {
        bindService(boundServiceIntent, mConnection, Context.BIND_AUTO_CREATE)
    }


    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        stopService(serviceIntent)
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        Log.d(TAG, "onStop")
    }
}