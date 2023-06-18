package com.udacity.shoestore.screen.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import java.util.UUID

class ShoeDetailViewModel(
    initialShoe: Shoe?,
) : ViewModel() {

    private val id = initialShoe?.id ?: UUID.randomUUID()
    var name = MutableLiveData(initialShoe?.name.orEmpty())
    var company = MutableLiveData(initialShoe?.company.orEmpty())
    var size = MutableLiveData(initialShoe?.size ?: .0)
    var description = MutableLiveData(initialShoe?.description.orEmpty())

    private val _eventCancel = MutableLiveData<Boolean>()
    val eventCancel: LiveData<Boolean> = _eventCancel

    fun cancel() {
        _eventCancel.value = true
    }

    fun onEventCancelCompleted() {
        _eventCancel.value = false
    }

    val shoe: Shoe
        get() = Shoe(
            id = id,
            name = name.value.orEmpty(),
            company = company.value.orEmpty(),
            size = size.value ?: .0,
            description = description.value.orEmpty(),
        )
}