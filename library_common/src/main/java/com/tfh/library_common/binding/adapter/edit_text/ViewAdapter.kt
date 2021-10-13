package com.tfh.library_common.binding.adapter.edit_text

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.tfh.lib_base.binding.command.BindingCommand

object ViewAdapter {

    /**
     * EditText输入文字改变的监听
     */
    @JvmStatic
    @BindingAdapter(value = ["textChanged"], requireAll = false)
    fun addTextChangedListener(editText: EditText, textChanged: BindingCommand<String?>?) {
        editText.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(text: CharSequence, i: Int, i1: Int, i2: Int) {

                textChanged?.execute(text.toString())
            }

            override fun afterTextChanged(editable: Editable) {}

        })
    }

}