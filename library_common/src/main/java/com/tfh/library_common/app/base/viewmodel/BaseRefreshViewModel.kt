package com.tfh.library_common.app.base.viewmodel

import com.tfh.lib_base.base.viewmodel.BaseViewModel
import com.tfh.lib_base.livedata.BooleanLiveData

/**
 * @author fenghui
 * @date 2021/7/16.
 * @description 刷新加载列表viewmodel
 */
abstract class BaseRefreshViewModel : BaseViewModel(){

    var isRefresh = BooleanLiveData<Boolean>().apply {
        value = true
    }

    open fun refresh() {
        isRefresh.value = true
        refreshData()
    }

    open fun loadMore() {
        isRefresh.value = false
        loadMoreData()
    }

    abstract fun refreshData()

    abstract fun loadMoreData()

}