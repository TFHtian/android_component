package com.tfh.library_common.widget.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import com.tfh.library_common.R
import com.tfh.library_common.util.TypeFaceUtil

/**
 * 带有自定义字体RadioButton。
 *
 */
class TypefaceRadioButton : AppCompatRadioButton {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CommonTypefaceTextView, 0, 0)
            val typefaceType = typedArray.getInt(R.styleable.CommonTypefaceTextView_common_typeface, 0)
            typeface = TypeFaceUtil.getTypeface(typefaceType)
            typedArray.recycle()
        }
    }

}