package com.tfh.lib_base.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tfh.library_base.R
import com.gyf.immersionbar.ImmersionBar
import com.tfh.lib_base.base.viewmodel.BaseViewModel
import com.tfh.lib_base.ext.getVmClazz
import com.tfh.lib_base.network.manager.NetState
import com.tfh.lib_base.network.manager.NetworkStateManager

/**
 * @author tianfenghui
 * @date 2021/4/20.
 * @description 基类activity 注入viewmodel和 databinding
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    lateinit var mBinding: DB

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init(savedInstanceState)
    }

    /**
     * 初始化
     */
    private fun init(savedInstanceState: Bundle?) {
        mBinding = DataBindingUtil.setContentView(this, layoutId())
        mBinding.lifecycleOwner = this
        mViewModel = createViewModel()
        setStatusBar()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer {
            onNetworkStateChanged(it)
        })
    }

    /**
     * 设置共同沉浸式样式
     */
    open fun setStatusBar(){
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .statusBarColor(R.color.base_white)
            .navigationBarColor(R.color.base_white)
            .init()
    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    /**
     * 注册UI 事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.commonChange.showDialog.observe(this, Observer {
            showLoading(it)
        })
        //关闭弹窗
        mViewModel.commonChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }

}