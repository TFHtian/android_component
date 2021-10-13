package com.tfh.library_common.ext

import android.text.TextUtils
import android.widget.Toast
import com.tfh.library_common.app.App

const val maxToastShowTime : Long = 1000
var lastToastTime : Long = 0
var lastMessage : String = ""

fun toastShort(text: String) = toast(text, Toast.LENGTH_SHORT)

fun toastLong(text: String) = toast(text, Toast.LENGTH_LONG)

fun toast(text: String, showStyle: Int) {
    if (TextUtils.equals(lastMessage, text)) {
        if (System.currentTimeMillis() - lastToastTime > maxToastShowTime) {
            Toast.makeText(App.instance, text, showStyle).show()
        }
    } else {
        Toast.makeText(App.instance, text, showStyle).show()
    }

    lastMessage = text
    lastToastTime = System.currentTimeMillis()
}
