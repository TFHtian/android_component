package com.tfh.main.ui.activity

import android.os.Bundle
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.tfh.library_common.app.base.activity.BaseActivity
import com.tfh.library_common.config.AppConstants
import com.tfh.library_common.route.RouteCenter
import com.tfh.library_common.util.CacheUtil
import com.tfh.main.R
import com.tfh.main.databinding.MainActivitySplashBinding
import com.tfh.main.ui.viewmodel.SplashVm
import kotlinx.coroutines.delay

/**
 * @author fenghui
 * @date 2021/8/16.
 * @description
 */
class SplashActivity : BaseActivity<SplashVm,MainActivitySplashBinding>(){

    override fun layoutId(): Int {
        return R.layout.main_activity_splash
    }

    override fun setStatusBar() {
        ImmersionBar.with(this)
            .titleBar(mBinding.topView)
            .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
            .init()
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewModel.launchUI {
            delay(1000)
            if (CacheUtil.checkLogin()){
                RouteCenter.navigate(AppConstants.Router.Main.A_MAIN)
            }else{
                RouteCenter.navigate(AppConstants.Router.Auth.A_AUTH)
            }
            overridePendingTransition(R.anim.common_screen_zoom_in, R.anim.common_screen_zoom_out)
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

}