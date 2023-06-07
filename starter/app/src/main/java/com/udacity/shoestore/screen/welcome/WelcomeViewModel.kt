package com.udacity.shoestore.screen.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _gotoInstructionEvent = MutableLiveData<Boolean>()
    val gotoInstructionEvent: LiveData<Boolean> = _gotoInstructionEvent

    fun onNavigatedToInstructionScreen() {
        _gotoInstructionEvent.value = false
    }

    fun gotoInstruction() {
        _gotoInstructionEvent.value = true
    }
}