package com.tfh.library_common.app

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.kingja.loadsir.core.LoadSir
import com.tencent.mmkv.MMKV
import com.tfh.lib_base.base.BaseApp
import com.tfh.lib_base.util.LogUtils
import com.tfh.library_common.BuildConfig
import com.tfh.library_common.widget.loadCallBack.EmptyCallback
import com.tfh.library_common.widget.loadCallBack.ErrorCallback
import com.tfh.library_common.widget.loadCallBack.LoadingCallback
import me.jessyan.autosize.AutoSize
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.onAdaptListener
import me.jessyan.autosize.utils.AutoSizeLog
import me.jessyan.autosize.utils.ScreenUtils
import java.util.*

/**
 * @author tianfenghui
 * @date 2021/5/4.
 * @description 全局
 */
class App : BaseApp(){

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MMKV.initialize(this)
        initAutoSizeDp()

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)

        //界面加载管理 初始化
        LoadSir.beginBuilder()
                .addCallback(LoadingCallback())//加载
                .addCallback(ErrorCallback())//错误
                .addCallback(EmptyCallback())//空
                .setDefaultCallback(LoadingCallback::class.java)//设置默认加载状态页
                .commit()
    }

    /**
     * 今日头条适配方案
     */
    private fun initAutoSizeDp() {
        AutoSize.checkAndInit(instance)
        AutoSizeConfig.getInstance().isUseDeviceSize = true
        //AutoSizeConfig.getInstance().isExcludeFontScale = true
        AutoSizeConfig.getInstance() //是否让框架支持自定义 Fragment 的适配参数, 由于这个需求是比较少见的, 所以须要使用者手动开启
                //如果没有这个需求建议不开启
                .setCustomFragment(true)
                .onAdaptListener = object : onAdaptListener {
            override fun onAdaptBefore(target: Any, activity: Activity) {
                //使用以下代码, 可以解决横竖屏切换时的屏幕适配问题
                //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会主动重绘当前页面, 所以这时您需要自行重绘当前页面
                //ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!

                val width = ScreenUtils.getScreenSize(activity)[0]
                val height = ScreenUtils.getScreenSize(activity)[1]
                LogUtils.debugInfo("宽度$width 高度$height")
                //防止屏幕旋转瞬间造成宽高交换
                if (width < height) {
                    AutoSizeConfig.getInstance().screenWidth = height
                    AutoSizeConfig.getInstance().screenHeight = width
                } else {
                    AutoSizeConfig.getInstance().screenWidth = width
                    AutoSizeConfig.getInstance().screenHeight = height
                }
            }

            override fun onAdaptAfter(target: Any, activity: Activity) {
                AutoSizeLog.d(
                        String.format(
                                Locale.ENGLISH,
                                "%s onAdaptAfter!",
                                target.javaClass.name
                        )
                )
            }
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}