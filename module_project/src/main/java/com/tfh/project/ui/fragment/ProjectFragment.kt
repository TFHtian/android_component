package com.tfh.project.ui.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.tfh.library_common.app.base.fragment.BaseFragment
import com.tfh.library_common.config.AppConstants
import com.tfh.project.R
import com.tfh.project.databinding.ProjectFragmentProjectBinding
import com.tfh.project.viewmodel.ProjectVm

/**
 * @author fenghui
 * @date 2021/8/23.
 * @description 项目
 */
@Route(path = AppConstants.Router.Project.F_PROJECT)
class ProjectFragment : BaseFragment<ProjectVm, ProjectFragmentProjectBinding>(){

    override fun layoutId(): Int {
        return R.layout.project_fragment_project
    }

    override fun initView(savedInstanceState: Bundle?) {
        with(mBinding){
            lifecycleOwner = this@ProjectFragment
        }
    }

}