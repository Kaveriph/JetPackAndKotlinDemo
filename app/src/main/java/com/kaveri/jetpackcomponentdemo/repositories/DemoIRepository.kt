package com.kaveri.jetpackcomponentdemo.repositories

interface DemoIRepository {

    fun saveDataInPref( saveUploadData : ((okMessage: String) -> Unit), message: String)

}
