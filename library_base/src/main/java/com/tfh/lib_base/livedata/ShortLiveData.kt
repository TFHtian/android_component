package com.tfh.lib_base.livedata

import androidx.lifecycle.MutableLiveData

class ShortLiveData : MutableLiveData<Short>() {
    override fun getValue(): Short {
        return super.getValue() ?: 0
    }
}