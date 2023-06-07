package com.udacity.shoestore.screen.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _navigateToWelcomeEvent = MutableLiveData<Boolean>()
    val navigateToWelcomeEvent: LiveData<Boolean> = _navigateToWelcomeEvent

    fun onNavigatedToWelcomeScreen() {
        _navigateToWelcomeEvent.value = false
    }

    fun login() {
        _navigateToWelcomeEvent.value = true
    }

    fun createNewAccount() {
        _navigateToWelcomeEvent.value = true
    }
}