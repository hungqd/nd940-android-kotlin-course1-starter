package com.udacity.shoestore.screen.instruction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionViewModel : ViewModel() {

    private val _navigateToShoeList = MutableLiveData<Boolean>()
    val navigateToShoeList: LiveData<Boolean> = _navigateToShoeList

    fun onNavigatedToShoeList() {
        _navigateToShoeList.value = false
    }

    fun toToShoeList() {
        _navigateToShoeList.value = true
    }
}