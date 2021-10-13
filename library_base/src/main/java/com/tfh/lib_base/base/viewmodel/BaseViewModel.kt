package com.tfh.lib_base.base.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfh.lib_base.livedata.event.EventLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * @author tianfenghui
 * @date 2021/4/20.
 * @description 基类viewmodel
 */
open class BaseViewModel : ViewModel(){

    val commonChange: CommonUiChange by lazy { CommonUiChange() }

    /**
     * 携程都在viewmodel作用域
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch { block() }

    /**
     * 携程流式处理
     */
    @FlowPreview
    fun <T> launchFlow(block: suspend () -> T): Flow<T> =
        flow { emit(block()) }

    /**
     * 封装可通知Activity/fragment 显示隐藏加载框 网络加载菊花
     */
    inner class CommonUiChange {
        //显示加载框
        val showDialog by lazy { EventLiveData<String>() }
        //隐藏
        val dismissDialog by lazy { EventLiveData<Boolean>() }
    }

    /**
     * 事件监听注册
     */
    interface BaseHandlers {

        fun clickBack(view: View)
    }

}