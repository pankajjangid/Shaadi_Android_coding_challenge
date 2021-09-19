package com.example.demoproject.ui.invite

interface InviteListener {


    fun onStarted()
    fun onSuccess(responseData: Any?)
    fun onFailure(message: String?)
}
