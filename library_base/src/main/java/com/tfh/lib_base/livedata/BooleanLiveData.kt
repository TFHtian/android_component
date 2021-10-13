package com.tfh.lib_base.livedata

import androidx.lifecycle.MutableLiveData

/**
 * 自定义的*类型 MutableLiveData 提供了默认值，避免取值的时候还要判空
 */
class BooleanLiveData<T> : MutableLiveData<Boolean>() {

    override fun getValue(): Boolean {
        return super.getValue() ?: false
    }
}

