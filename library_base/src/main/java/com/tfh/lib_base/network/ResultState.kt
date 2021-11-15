package com.tfh.lib_base.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tfh.lib_base.network.exception.AppException
import com.tfh.lib_base.network.exception.ExceptionHandle

sealed class ResultState<out T> {
    companion object {
        fun <T> onAppSuccess(data: T): ResultState<T> = Success(data)
        fun <T> onAppLoading(loadingMessage: String): ResultState<T> = Loading(loadingMessage)
        fun <T> onAppError(error: AppException): ResultState<T> = Error(error)
    }

    data class Loading(val loadingMessage: String) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: AppException) : ResultState<Nothing>()
}


/**
 * 处理返回值
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: BaseResponse<T>) {
    value = if (result.isSuccess()) ResultState.onAppSuccess(result.getResponseData()) else
        ResultState.onAppError(
            AppException(
                result.getResponseCode(),
                result.getResponseMsg()
            )
        )
}

/**
 * 不处理返回值 直接返回请求结果
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: T) {
    value = ResultState.onAppSuccess(result)
}

/**
 * 异常转换异常处理
 */
fun <T> MutableLiveData<ResultState<T>>.paresException(e: Throwable) {
    Log.e("aaa", e.message ?: "")
    this.value = ResultState.onAppError(ExceptionHandle.handleException(e))
}

