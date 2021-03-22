package com.kaveri.jetpackcomponentdemo.repositories

class DemoRepository : DemoIRepository{

    override fun saveDataInPref(saveUploadData: (okMessage: String) -> Unit, message: String) {
        // create a shared prefernce and store this information inside in local storage
        // and reply back to the caller using lambda method passed
        saveUploadData.invoke("cached status for: $message")
    }

}