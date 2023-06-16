package com.udacity.shoestore.screen.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()

    val shoes: LiveData<List<Shoe>> = _shoes

    fun addShoe(shoe: Shoe) {
        val shoeList = _shoes.value.orEmpty().toMutableList()
        shoeList.add(shoe)
        _shoes.value = shoeList
    }
}