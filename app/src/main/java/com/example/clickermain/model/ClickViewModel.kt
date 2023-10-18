package com.example.clickermain.model

import androidx.lifecycle.ViewModel

class ClickViewModel : ViewModel() {
    private var _ins = 0
    val ins: Int
        get() = _ins
    private var _outs = 0
    val outs: Int
        get() = _outs
    private var _total = 0
    val total: Int
        get() = _total
    private var _capacity = 0
    val capacity: Int
        get() = _capacity

    fun getTotal() {
        _total = _ins.minus(_outs)
    }

    fun setIns() {
        _ins = _ins.plus(1)
    }
    fun setCapacity(limits:Int) {
        _capacity=limits
    }

    fun setOuts() {
        _outs = _outs.plus(1)
    }


}