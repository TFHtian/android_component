package com.tfh.auth.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.tfh.auth.R
import com.tfh.auth.databinding.AuthFragmentRegisterBinding
import com.tfh.auth.viewmodel.AuthCheckVm
import com.tfh.auth.viewmodel.RegisterVm
import com.tfh.library_common.app.base.fragment.BaseFragment
import com.tfh.library_common.ext.toastShort
import com.tfh.library_common.ext.yes

/**
 * @author fenghui
 * @date 2021/8/20.
 * @description 注册
 */
class RegisterFragment : BaseFragment<RegisterVm,AuthFragmentRegisterBinding>(), RegisterVm.Handle{

    private val actViewModel by activityViewModels<AuthCheckVm>()

    override fun layoutId(): Int {
        return R.layout.auth_fragment_register
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(mBinding){
            lifecycleOwner = this@RegisterFragment
            viewModel = mViewModel
            handle = this@RegisterFragment
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
        mViewModel.isRePasswordEmpty.observe(this){
            it.yes {
                viewShakeAnim(mBinding.etConfirmPassword)
            }
        }
        mViewModel.isUnAgreement.observe(this){
            it.yes {
                toastShort(resources.getString(R.string.auth_password_un_agreement))
                viewShakeAnim(mBinding.etConfirmPassword)
            }
        }
        mViewModel.isAuthPass.observe(this){
            actViewModel.isRegisterSuccess.value = true
        }
    }

    override fun toLogin(view: View) {
        actViewModel.stateLogin.value = true
    }

    override fun toRegister(view: View) {
        mViewModel.checkInfo()
    }

    private fun viewShakeAnim(view: View){
        YoYo.with(Techniques.Shake)
            .duration(500)
            .playOn(view)
    }

}