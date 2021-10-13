package com.tfh.lib_base.base.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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
 * @description 基类fragment 注入viewmodel 和 databinding
 */
 abstract class BaseVmDbFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    private val handler = Handler()

    //是否第一次加载
    private var isFirst: Boolean = true

    lateinit var mBinding: DB

    lateinit var mViewModel: VM

    lateinit var mActivity: AppCompatActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isFirst = true
        mViewModel = createViewModel()
        setStatusBar()
        initView(savedInstanceState)
        createObserver()
        registerUiChange()
        initData()
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
     * 当前Fragment绑定的视图布局
     */
    abstract fun layoutId(): Int

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
     * 初始化view
     */
    abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    abstract fun lazyLoadData()

    /**
     * 创建观察者
     */
    abstract fun createObserver()

    override fun onResume() {
        super.onResume()
        onVisible()
    }

    /**
     * 是否需要懒加载
     */
    private fun onVisible() {
        if (lifecycle.currentState == Lifecycle.State.STARTED && isFirst) {
            // 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿
            handler.postDelayed( {
                lazyLoadData()
                //在Fragment中，只有懒加载过了才能开启网络变化监听
                NetworkStateManager.instance.mNetworkStateCallback.observe(
                    this,
                    Observer {
                        //不是首次订阅时调用方法，防止数据第一次监听错误
                        if (!isFirst) {
                            onNetworkStateChanged(it)
                        }
                    })
                isFirst = false
            },lazyLoadTime())
        }
    }

    /**
     * Fragment执行onCreate后触发的方法
     */
    open fun initData() {}

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    /**
     * 注册 UI 事件
     */
    private fun registerUiChange() {
        mViewModel.commonChange.showDialog.observe(this, Observer {
            showLoading(it)
        })
        mViewModel.commonChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }

    /**
     * 延迟加载 防止 切换动画还没执行完毕时数据就已经加载好了，这时页面会有渲染卡顿  bug
     * 这里传入你想要延迟的时间，延迟时间可以设置比转场动画时间长一点 单位： 毫秒
     * 不传默认 300毫秒
     * @return Long
     */
    open fun lazyLoadTime(): Long {
        return 300
    }

}