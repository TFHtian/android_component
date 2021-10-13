package com.tfh.auth.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.tfh.auth.R
import com.tfh.auth.databinding.AuthFragmentLoginBinding
import com.tfh.auth.viewmodel.AuthCheckVm
import com.tfh.auth.viewmodel.LoginVm
import com.tfh.lib_base.util.KvUtil
import com.tfh.library_common.app.base.fragment.BaseFragment
import com.tfh.library_common.config.AppConstants
import com.tfh.library_common.config.AppConstants.KvKey.LOGIN_ACCOUNT
import com.tfh.library_common.config.AppConstants.KvKey.LOGIN_PASSWORD
import com.tfh.library_common.ext.yes
import com.tfh.library_common.route.RouteCenter

/**
 * @author fenghui
 * @date 2021/8/20.
 * @description 登录
 */
class LoginFragment : BaseFragment<LoginVm, AuthFragmentLoginBinding>(), LoginVm.Handle{

    private val actViewModel by activityViewModels<AuthCheckVm>()

    override fun layoutId(): Int {
        return R.layout.auth_fragment_login
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(mBinding){
            lifecycleOwner = this@LoginFragment
            viewModel = mViewModel
            handle = this@LoginFragment
        }
    }

    override fun createObserver() {
        mViewModel.isAccountEmpty.observe(this){
            it.yes {
                viewShakeAnim(mBinding.etAccount)
            }
        }
        mViewModel.isPasswordEmpty.observe(this){
            it.yes {
                viewShakeAnim(mBinding.etPassword)
            }
        }
        mViewModel.isAuthPass.observe(this){
            goToMain()
        }
        actViewModel.isRegisterSuccess.observe(this){
            mViewModel.account.value = KvUtil.decodeString(LOGIN_ACCOUNT)
            mViewModel.password.value = KvUtil.decodeString(LOGIN_PASSWORD)
        }
    }

    override fun toLogin(view: View) {
        mViewModel.checkInfo()
    }

    override fun toRegister(view: View) {
        actViewModel.stateRegister.value = true
    }

    override fun toVisitor(view: View) {
        goToMain()
    }

    private fun goToMain(){
        RouteCenter.navigate(AppConstants.Router.Main.A_MAIN)
        activity?.let {
            it.overridePendingTransition(R.anim.common_screen_zoom_in, R.anim.common_screen_zoom_out)
            it.finish()
        }
    }

    private fun viewShakeAnim(view: View){
        YoYo.with(Techniques.Shake)
            .duration(500)
            .playOn(view)
    }

}