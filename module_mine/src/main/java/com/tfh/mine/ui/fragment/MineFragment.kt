package com.tfh.mine.ui.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.tfh.library_common.app.base.fragment.BaseFragment
import com.tfh.library_common.config.AppConstants
import com.tfh.mine.R
import com.tfh.mine.databinding.MineFragmentMineBinding
import com.tfh.mine.viewmodel.MineVm

/**
 * @author fenghui
 * @date 2021/8/23.
 * @description 我的
 */
@Route(path = AppConstants.Router.Mine.F_MINE)
class MineFragment : BaseFragment<MineVm,MineFragmentMineBinding>(){

    override fun layoutId(): Int {
        return R.layout.mine_fragment_mine
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(mBinding){
            lifecycleOwner = this@MineFragment
        }
    }

}