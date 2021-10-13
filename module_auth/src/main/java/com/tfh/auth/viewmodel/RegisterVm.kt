package com.tfh.auth.viewmodel

import com.tfh.lib_base.util.KvUtil
import com.tfh.library_common.config.AppConstants.KvKey.LOGIN_ACCOUNT
import com.tfh.library_common.config.AppConstants.KvKey.LOGIN_PASSWORD

class RegisterVm : BaseAuthViewModel(){

    fun checkInfo(){
        if (account.value.isNullOrEmpty()){
            isAccountEmpty.value = true
            return
        }
        if (password.value.isNullOrEmpty()){
            isPasswordEmpty.value = true
            return
        }
        if (rePassword.value.isNullOrEmpty()){
            isRePasswordEmpty.value = true
            return
        }
        if (password.value!=rePassword.value){
            isUnAgreement.value = true
            return
        }
        KvUtil.encode(LOGIN_ACCOUNT,account.value)
        KvUtil.encode(LOGIN_PASSWORD,password.value)
        isAuthPass.value = true
    }

    interface Handle : BaseHandle{}

}