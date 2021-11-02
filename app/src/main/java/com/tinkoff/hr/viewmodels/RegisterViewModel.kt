package com.tinkoff.hr.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    private val mutableCode = MutableLiveData<String>()
    val code: LiveData<String> get() = mutableCode

    fun setCode(code: String){
        mutableCode.value = code
    }
}