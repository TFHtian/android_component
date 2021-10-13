package com.tfh.library_common.app.base.fragment

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.tfh.lib_base.base.viewmodel.BaseViewModel
import com.wuhenzhizao.titlebar.widget.CommonTitleBar

/**
 * @author fenghui
 * @date 2021/7/16.
 * @description 基类统一顶部导航栏
 */
abstract class BaseTitleBarFragment<VM : BaseViewModel, DB : ViewDataBinding> : BaseLoadSirFragment<VM, DB>() {

    private var titleBar: CommonTitleBar? = null

    override fun initView(savedInstanceState: Bundle?) {
        initTitleBar()
    }

    private fun initTitleBar(){
        titleBar = getTitleBar()
        titleBar?.setListener { _, action, _ ->
            when(action){
                CommonTitleBar.ACTION_LEFT_TEXT ->{
                    clickTitleBarLeft()
                }
                CommonTitleBar.ACTION_LEFT_BUTTON ->{
                    clickTitleBarLeft()
                }
                CommonTitleBar.ACTION_RIGHT_TEXT ->{
                    clickTitleBarRight()
                }
                CommonTitleBar.ACTION_RIGHT_BUTTON->{
                    clickTitleBarRight()
                }
            }
        }
    }

    open fun clickTitleBarLeft(){

    }

    open fun clickTitleBarRight(){

    }

    abstract fun getTitleBar(): CommonTitleBar?
}