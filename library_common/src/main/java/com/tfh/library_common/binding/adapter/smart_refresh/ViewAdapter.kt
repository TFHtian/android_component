package com.tfh.library_common.binding.adapter.smart_refresh

import androidx.databinding.BindingAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.tfh.lib_base.binding.command.BindingCommand

object ViewAdapter {

    //下拉刷新命令
    @JvmStatic
    @BindingAdapter("onRefreshCommand")
    fun onRefreshCommand(
        smartRefreshLayout: SmartRefreshLayout,
        onRefreshCommand: BindingCommand<*>?
    ) {
        smartRefreshLayout.setOnRefreshListener { refreshLayout: RefreshLayout? -> onRefreshCommand?.execute() }
    }

    @JvmStatic
    @BindingAdapter("onLoadMoreCommand")
    fun onLoadMoreCommand(
        smartRefreshLayout: SmartRefreshLayout,
        onLoadCommand: BindingCommand<*>?
    ) {
        smartRefreshLayout.setOnLoadMoreListener { refreshLayout: RefreshLayout? -> onLoadCommand?.execute() }
    }

}