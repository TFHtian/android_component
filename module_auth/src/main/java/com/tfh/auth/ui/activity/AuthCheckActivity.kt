package com.tfh.auth.ui.activity

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.observe
import com.alibaba.android.arouter.facade.annotation.Route
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.gyf.immersionbar.ImmersionBar
import com.tfh.auth.R
import com.tfh.auth.databinding.AuthActivityCheckBinding
import com.tfh.auth.entity.AuthState
import com.tfh.auth.ui.fragment.LoginFragment
import com.tfh.auth.ui.fragment.RegisterFragment
import com.tfh.auth.viewmodel.AuthCheckVm
import com.tfh.library_common.app.base.activity.BaseActivity
import com.tfh.library_common.config.AppConstants
import com.tfh.library_common.ext.yes

/**
 * @author fenghui
 * @date 2021/8/19.
 * @description 用户登录验证检测
 */
@Route(path = AppConstants.Router.Auth.A_AUTH)
class AuthCheckActivity : BaseActivity<AuthCheckVm,AuthActivityCheckBinding>(){

    private var loginFragment: LoginFragment? = null
    private var registerFragment: RegisterFragment? = null

    override fun layoutId(): Int {
        return R.layout.auth_activity_check
    }

    override fun setStatusBar() {
        ImmersionBar.hideStatusBar(window)
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(mBinding){
            lifecycleOwner = this@AuthCheckActivity
            viewModel = mViewModel
        }
        mViewModel.stateText.value = resources.getString(R.string.auth_login)
        currentStateFragment(AuthState.LOGIN)
    }

    override fun createObserver() {
        mViewModel.stateLogin.observe(this) {
            it.yes {
                currentStateFragment(AuthState.LOGIN)
                mViewModel.stateText.value = resources.getString(R.string.auth_login)
            }
        }
        mViewModel.stateRegister.observe(this){
            it.yes {
                currentStateFragment(AuthState.REGISTER)
                mViewModel.stateText.value = resources.getString(R.string.auth_register)
            }
        }
        mViewModel.isRegisterSuccess.observe(this){
            it.yes {
                currentStateFragment(AuthState.LOGIN)
                mViewModel.stateText.value = resources.getString(R.string.auth_login)
            }
        }
    }

    private fun currentStateFragment(state: AuthState){
        stateTextAnim()
        val transaction = supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.common_right_in_enter,
                        R.anim.common_right_in_exit,
                        R.anim.common_right_out_enter,
                        R.anim.common_right_out_exit
                )
                .addToBackStack(null)
        hideFragment(transaction)
        when(state){
            AuthState.LOGIN ->{
                if (loginFragment == null) {
                    loginFragment = LoginFragment()
                    transaction.add(R.id.fl_container, loginFragment!!)
                } else {
                    transaction.show(loginFragment!!)
                }
            }
            AuthState.REGISTER ->{
                if (registerFragment == null) {
                    registerFragment = RegisterFragment()
                    transaction.add(R.id.fl_container, registerFragment!!)
                } else {
                    transaction.show(registerFragment!!)
                }
            }
        }
        transaction.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        if (loginFragment != null) {
            transaction.hide(loginFragment!!)
        }
        if (registerFragment != null) {
            transaction.hide(registerFragment!!)
        }
    }

    private fun stateTextAnim(){
        YoYo.with(Techniques.SlideInRight)
            .duration(500)
            .playOn(mBinding.tvType)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}