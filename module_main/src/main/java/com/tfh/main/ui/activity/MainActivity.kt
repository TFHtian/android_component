package com.tfh.main.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.tfh.lib_base.ext.lifecycle.KtxActivityManger
import com.tfh.library_common.app.base.activity.BaseActivity
import com.tfh.library_common.config.AppConstants
import com.tfh.library_common.ext.toastShort
import com.tfh.library_common.route.RouteCenter
import com.tfh.main.R
import com.tfh.main.databinding.MainActivityMainBinding
import com.tfh.main.ui.adapter.MainTabAdapter
import com.tfh.main.ui.viewmodel.MainVm
import me.majiajie.pagerbottomtabstrip.NavigationController
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem
import me.majiajie.pagerbottomtabstrip.item.NormalItemView

@Route(path = AppConstants.Router.Main.A_MAIN)
class MainActivity : BaseActivity<MainVm,MainActivityMainBinding>() {

    private var touchTime: Long = 0L
    private lateinit var navigationController: NavigationController

    override fun layoutId(): Int {
        return R.layout.main_activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        initTabViewPager()
    }

    private fun initTabViewPager(){
        val homeFragment  = RouteCenter.navigate(AppConstants.Router.Home.F_HOME) as Fragment
        val squareFragment = RouteCenter.navigate(AppConstants.Router.Square.F_SQUARE) as Fragment
        val projectFragment = RouteCenter.navigate(AppConstants.Router.Project.F_PROJECT) as Fragment
        val mineFragment = RouteCenter.navigate(AppConstants.Router.Mine.F_MINE) as Fragment
        val fragments = arrayListOf(homeFragment, squareFragment, projectFragment, mineFragment)
        navigationController = mBinding.mainTab.custom()
            .addItem(
                newItem(
                    R.mipmap.main_home_n,
                    R.mipmap.main_home_s,
                    this.resources.getString(R.string.main_tab_home)
                )
            )
            .addItem(
                newItem(
                    R.mipmap.main_square_n,
                    R.mipmap.main_square_s,
                    this.resources.getString(R.string.main_tab_square)
                )
            )
            .addItem(
                newItem(
                    R.mipmap.main_project_n,
                    R.mipmap.main_project_s,
                    this.resources.getString(R.string.main_tab_project)
                )
            )
            .addItem(
                newItem(
                    R.mipmap.main_mine_n,
                    R.mipmap.main_mine_s,
                    this.resources.getString(R.string.main_tab_mine)
                )
            )
            .build()

        mBinding.mainViewPage.run {
            adapter = MainTabAdapter(fragments,supportFragmentManager)
            navigationController.setupWithViewPager(this)
            navigationController.setSelect(0)
        }
    }

    private fun newItem(drawable: Int, checkedDrawable: Int, text: String): BaseTabItem {
        val normalItemView = NormalItemView(this)
        normalItemView.initialize(drawable, checkedDrawable, text)
        normalItemView.setTextDefaultColor(resources.getColor(R.color.main_bottom_tv_n))
        normalItemView.setTextCheckedColor(resources.getColor(R.color.main_bottom_tv_s))
        return normalItemView
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (System.currentTimeMillis() - touchTime < 2000L) {
            KtxActivityManger.finishAllActivity()
        } else {
            touchTime = System.currentTimeMillis()
            toastShort(getString(R.string.main_press_again))
        }
    }

}