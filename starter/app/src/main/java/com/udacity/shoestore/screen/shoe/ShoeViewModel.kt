package com.udacity.shoestore.screen.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>()

    val shoes: LiveData<List<Shoe>> = _shoes

    private val _navigateToShoeDetail = MutableLiveData<Shoe?>()
    val eventViewShoeDetail: LiveData<Shoe?> = _navigateToShoeDetail
    fun gotoShoeDetail(shoe: Shoe) {
        _navigateToShoeDetail.value = shoe
    }

    fun onNavigatedToShoeDetail() {
        _navigateToShoeDetail.value = null
    }

    fun saveShoe(shoe: Shoe) {
        val allShoes = shoes.value.orEmpty().toMutableList()
        val index = allShoes.indexOfFirst { it.id == shoe.id }
        if (index >= 0) {
            allShoes[index] = shoe
        } else {
            allShoes.add(shoe)
        }
        _shoes.value = allShoes
    }
}