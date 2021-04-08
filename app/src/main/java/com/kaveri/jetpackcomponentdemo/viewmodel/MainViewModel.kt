package com.kaveri.jetpackcomponentdemo.viewmodel

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaveri.jetpackcomponentdemo.dao.ListItem
import com.kaveri.jetpackcomponentdemo.navigators.MainNavigator
import com.kaveri.jetpackcomponentdemo.repositories.DemoRepository
import com.kaveri.jetpackcomponentdemo.viewholders.NameViewHolder
import java.lang.ref.WeakReference

class MainViewModel<T>(val demoRepository: DemoRepository): ViewModel() {
    var screenName : MutableLiveData<String> = MutableLiveData()
    var messageFRomService = MutableLiveData<String>()
    private lateinit var navigator:WeakReference<T>

    fun setScreenName(screenName : String) {
        this.screenName.value = screenName
    }

    fun setNavigator(navigator:T) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator() : T? {
        return navigator.get()
    }

    fun uploadData() {
        ( getNavigator() as MainNavigator).uploadData()

    }

    fun uploadDataViaBoundservice() {
        (getNavigator() as MainNavigator).uploadDataViaBoundservice()
    }

    fun saveDataLocally(message:String) {
        demoRepository.saveDataInPref(( {
            messageFRomService.postValue(it)
        }), message = message)
    }

}