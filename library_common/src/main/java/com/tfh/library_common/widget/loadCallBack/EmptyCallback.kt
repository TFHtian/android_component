package com.tfh.library_common.widget.loadCallBack

import com.kingja.loadsir.callback.Callback
import com.tfh.library_common.R

class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.common_layout_empty
    }

}