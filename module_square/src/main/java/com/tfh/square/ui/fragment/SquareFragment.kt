package com.tfh.square.ui.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.tfh.library_common.app.base.fragment.BaseFragment
import com.tfh.library_common.config.AppConstants
import com.tfh.square.R
import com.tfh.square.databinding.SquareFragmentSquareBinding
import com.tfh.square.viewmodel.SquareVm

/**
 * @author fenghui
 * @date 2021/8/23.
 * @description 广场
 */
@Route(path = AppConstants.Router.Square.F_SQUARE)
class SquareFragment : BaseFragment<SquareVm, SquareFragmentSquareBinding>(){

    override fun layoutId(): Int {
        return R.layout.square_fragment_square
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(mBinding){
            lifecycleOwner = this@SquareFragment
        }
    }

}