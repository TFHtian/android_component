package com.tfh.library_common.app.base.activity

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.tfh.lib_base.base.viewmodel.BaseViewModel
import com.wuhenzhizao.titlebar.widget.CommonTitleBar

/**
 * @author fenghui
 * @date 2021/7/16.
 * @description 处理导航栏
 *
 */
abstract class BaseTitleBarActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseLoadSirActivity<VM, DB>() {

    private var titleBar: CommonTitleBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTitleBar()
    }

    private fun initTitleBar(){
        titleBar = getTitleBar()
        titleBar?.setListener { _, action, _ ->
            // CommonTitleBar.ACTION_LEFT_TEXT;        // 左边TextView被点击
            // CommonTitleBar.ACTION_LEFT_BUTTON;      // 左边ImageBtn被点击
            // CommonTitleBar.ACTION_RIGHT_TEXT;       // 右边TextView被点击
            // CommonTitleBar.ACTION_RIGHT_BUTTON;     // 右边ImageBtn被点击
            // CommonTitleBar.ACTION_SEARCH;           // 搜索框被点击,搜索框不可输入的状态下会被触发
            // CommonTitleBar.ACTION_SEARCH_SUBMIT;    // 搜索框输入状态下,键盘提交触发，此时，extra为输入内容
            // CommonTitleBar.ACTION_SEARCH_VOICE;     // 语音按钮被点击
            // CommonTitleBar.ACTION_SEARCH_DELETE;    // 搜索删除按钮被点击
            // CommonTitleBar.ACTION_CENTER_TEXT;      // 中间文字点击
            when(action){
                CommonTitleBar.ACTION_LEFT_TEXT ->{
                    finish()
                }
                CommonTitleBar.ACTION_LEFT_BUTTON ->{
                    finish()
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

    open fun clickTitleBarRight(){

    }

    abstract fun getTitleBar(): CommonTitleBar?

}