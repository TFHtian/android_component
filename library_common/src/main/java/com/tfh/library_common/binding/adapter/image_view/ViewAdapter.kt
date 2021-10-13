package com.tfh.library_common.binding.adapter.image_view

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import com.tfh.library_common.ext.loadUrl

object ViewAdapter {

    @JvmStatic
    @BindingAdapter(value = ["url", "placeholderRes", "errorRes"], requireAll = false)
    fun setImageUri(
        imageView: ImageView,
        url: String?,
        placeholderRes: Drawable,
        errorRes: Drawable? = null
    ) {
        if (!TextUtils.isEmpty(url)) {
            imageView.loadUrl(url, placeholderRes, errorRes)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["src"], requireAll = false)
    fun setImageRes(imageView: ImageView, resId: ObservableInt?) {
        if (resId == null) {
            return
        }
        if (resId.get() != 0) {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(resId.get())
        } else {
            imageView.visibility = View.INVISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["imgRes", "imgBgRes"], requireAll = false)
    fun setImage(imageView: ImageView, resDrawable: Drawable?, bg: Drawable?) {
        if (resDrawable != null)
            imageView.setImageDrawable(resDrawable)
        if (bg!=null) imageView.background = bg
    }

}