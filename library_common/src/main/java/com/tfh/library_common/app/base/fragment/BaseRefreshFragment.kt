package com.tfh.library_common.app.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.tfh.library_common.app.base.viewmodel.BaseRefreshViewModel

/**
 * @author fenghui
 * @date 2021/7/16.
 * @description 刷新列表基类
 */
abstract class BaseRefreshFragment<VM : BaseRefreshViewModel, DB : ViewDataBinding> : BaseTitleBarFragment<VM, DB>()
                    , OnRefreshListener, OnLoadMoreListener {

    open lateinit var mRefreshLayout: SmartRefreshLayout

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        initRefreshView()
    }

    private fun initRefreshView() {
        mRefreshLayout = getRefreshLayout()
        with(mRefreshLayout){
            setRefreshHeader(ClassicsHeader(activity))
            setRefreshFooter(ClassicsFooter(activity))
            setOnRefreshListener(this@BaseRefreshFragment)
            setOnLoadMoreListener(this@BaseRefreshFragment)
        }
    }

    abstract fun getRefreshLayout(): SmartRefreshLayout

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mViewModel.refresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mViewModel.loadMore()
    }

}