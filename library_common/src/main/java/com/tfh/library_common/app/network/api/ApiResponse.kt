package com.tfh.library_common.app.network.api

import com.tfh.lib_base.network.BaseResponse

data class ApiResponse<T>(var result: Int, var code: Int, var message: String,var data: T) :
    BaseResponse<T>() {

    override fun isSuccess() = result == 1

    override fun getResponseCode() = code

    override fun getResponseData() = data

    override fun getResponseMsg() = message

}