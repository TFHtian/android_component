package com.tfh.library_common.ext
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tfh.library_common.widget.dialog.QMUITipDialogCustom

/**
 * loading 加载框
 */
private var loadingDialogBuilder: QMUITipDialogCustom? = null

/**
 * 打开等待框
 */
fun AppCompatActivity.showLoadingExt(message: String = "请求网络中") {
    if (!this.isFinishing) {
        if (loadingDialogBuilder == null) {
            loadingDialogBuilder =
                    QMUITipDialogCustom.Builder(this).setIconType(
                            QMUITipDialogCustom.Builder.ICON_TYPE_LOADING)
                            .setTipWord("请稍等...").create()
        }
        loadingDialogBuilder?.show()
    }
}


/**
 * 打开等待框
 */
fun Fragment.showLoadingExt(message: String = "请求网络中") {
    activity?.let {
        if (!it.isFinishing) {
            if (loadingDialogBuilder == null) {
                loadingDialogBuilder =
                        QMUITipDialogCustom.Builder(it).setIconType(
                                QMUITipDialogCustom.Builder.ICON_TYPE_LOADING)
                                .setTipWord("请稍等...").create()
            }
            loadingDialogBuilder?.show()
        }
    }
}

/**
 * 关闭等待框
 */
fun AppCompatActivity.dismissLoadingExt() {
    loadingDialogBuilder?.dismiss()
    loadingDialogBuilder = null
}

/**
 * 关闭等待框
 */
fun Fragment.dismissLoadingExt() {
    loadingDialogBuilder?.dismiss()
    loadingDialogBuilder = null
}
