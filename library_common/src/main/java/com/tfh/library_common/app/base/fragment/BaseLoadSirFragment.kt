package com.tfh.library_common.app.base.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.kingja.loadsir.core.LoadService
import com.tfh.lib_base.base.viewmodel.BaseViewModel
import com.tfh.library_common.ext.loadServiceInit

/**
 * @author fenghui

 * @date 2021/5/7.
 * @description 带界面管理状态基类Fragment
 */
abstract class BaseLoadSirFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseFragment<VM, DB>(){

    //界面状态管理者
    lateinit var loadsir: LoadService<Any>

    //加载视图view
    abstract fun loadSirView() : View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //状态页配置
        loadsir = loadServiceInit(loadSirView()) {
            //点击重试时触发的操作
            onReLoad()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    //loadSir 重试
    open fun onReLoad(){}

}