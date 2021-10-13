package com.tfh.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import com.tfh.lib_base.base.viewmodel.BaseViewModel

class AuthCheckVm : BaseViewModel(){

    var stateLogin = MutableLiveData<Boolean>()
    var stateRegister = MutableLiveData<Boolean>()
    var stateText = MutableLiveData<String>()
    var isRegisterSuccess = MutableLiveData<Boolean>()
}