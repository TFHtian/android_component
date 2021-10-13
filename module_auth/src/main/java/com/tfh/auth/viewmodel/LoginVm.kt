package com.tfh.auth.viewmodel

import android.view.View

class LoginVm : BaseAuthViewModel(){

    fun checkInfo(){
        if (account.value.isNullOrEmpty()){
            isAccountEmpty.value = true
            return
        }
        if (password.value.isNullOrEmpty()){
            isPasswordEmpty.value = true
            return
        }
        isAuthPass.value = true
    }

    interface Handle : BaseHandle{

        fun toVisitor(view: View)
    }

}