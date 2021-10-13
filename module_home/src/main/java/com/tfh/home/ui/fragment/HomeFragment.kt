package com.tfh.home.ui.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.tfh.home.R
import com.tfh.home.databinding.HomeFragmentHomeBinding
import com.tfh.home.viewmodel.HomeVm
import com.tfh.library_common.app.base.fragment.BaseFragment
import com.tfh.library_common.config.AppConstants

/**
 * @author fenghui
 * @date 2021/8/23.
 * @description 主页
 */
@Route(path = AppConstants.Router.Home.F_HOME)
class HomeFragment : BaseFragment<HomeVm,HomeFragmentHomeBinding>(){

    override fun layoutId(): Int {
        return R.layout.home_fragment_home
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(mBinding){
            lifecycleOwner = this@HomeFragment
        }
        ImmersionBar.with(this)
                .titleBar(mBinding.toolbar)
                .init()
    }

}