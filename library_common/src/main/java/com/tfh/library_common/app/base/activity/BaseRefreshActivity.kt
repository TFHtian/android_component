package com.tfh.library_common.app.base.activity

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
abstract class BaseRefreshActivity<VM : BaseRefreshViewModel, DB : ViewDataBinding> : BaseTitleBarActivity<VM, DB>()
                    , OnRefreshListener, OnLoadMoreListener {

    open lateinit var mRefreshLayout: SmartRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRefreshView()
    }

    private fun initRefreshView() {
        mRefreshLayout = getRefreshLayout()
        with(mRefreshLayout){
            setRefreshHeader(ClassicsHeader(this@BaseRefreshActivity))
            setRefreshFooter(ClassicsFooter(this@BaseRefreshActivity))
            setOnRefreshListener(this@BaseRefreshActivity)
            setOnLoadMoreListener(this@BaseRefreshActivity)
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