package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesListViewModel : ViewModel() {

    private val _shoesLiveData = MutableLiveData<MutableList<Shoe>>()
    val shoesLiveData: LiveData<MutableList<Shoe>>
        get() = _shoesLiveData

    init {
        _shoesLiveData.postValue(mutableListOf())
    }

    fun addShoe(shoe: Shoe) {
        _shoesLiveData.value?.add(shoe)
    }

    fun isShoesListEmpty(): Boolean {
        return shoesLiveData.value?.isEmpty() ?: true
    }
}
