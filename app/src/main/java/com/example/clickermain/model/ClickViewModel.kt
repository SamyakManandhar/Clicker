package com.example.clickermain.model

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clickermain.R

class ClickViewModel : ViewModel() {
    private val _ins = MutableLiveData<Int>()
    val ins: LiveData<Int> = _ins

    private val _outs = MutableLiveData<Int>()
    val outs: LiveData<Int> = _outs

    private val _total = MutableLiveData<Int>()
    val total: LiveData<Int> = _total

    private val _capacity = MutableLiveData<Int>(0)
    val capacity: LiveData<Int> = _capacity

    init {
        reset()
    }

    private fun getTotal() {
        _total.value = _ins.value?.minus(_outs.value!!)
    }


    fun setIns() {
        _ins.value = _ins.value?.plus(1)
        getTotal()
    }

    fun setCapacity(limits: Int) {
        _capacity.value = limits
    }

    fun setOuts() {
        _outs.value = _outs.value?.plus(1)
        getTotal()
    }

    fun checkCap(): Boolean {
        return _capacity.value != 0
    }

    fun checkLimit(): Boolean {
        return _total.value!! >= _capacity.value!!
    }

    fun reset() {
        _ins.value = 0
        _outs.value = 0
        _total.value = 0
    }
}
