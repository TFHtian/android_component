package com.tfh.auth.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.tfh.lib_base.base.viewmodel.BaseViewModel

open class BaseAuthViewModel : BaseViewModel(){

    var account = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var rePassword = MutableLiveData<String>()
    var isAccountEmpty = MutableLiveData<Boolean>()
    var isPasswordEmpty = MutableLiveData<Boolean>()
    var isRePasswordEmpty = MutableLiveData<Boolean>()
    var isUnAgreement = MutableLiveData<Boolean>()
    var isAuthPass = MutableLiveData<Boolean>()

    interface BaseHandle{

        fun toLogin(view: View)

        fun toRegister(view: View)
    }

}