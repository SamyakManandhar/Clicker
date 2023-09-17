package com.example.clickermain.model

import androidx.lifecycle.ViewModel

class ClickViewModel: ViewModel() {
    class ClickViewModel: ViewModel() {
        private var _ins=0
        val ins: Int
            get() = _ins
        private var _outs =0
        val outs: Int
            get() = _outs
        private var _total=0
        val total: Int
            get() = _total

        fun getTotal() {
            _total=_total.minus(_outs)
        }
        fun setIns(){
            _ins= _ins.plus(1)
        }
        fun setOuts(){
            _outs= _outs.minus(1)
        }

    }
}