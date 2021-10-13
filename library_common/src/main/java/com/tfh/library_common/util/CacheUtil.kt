package com.tfh.library_common.util

import com.tfh.lib_base.util.KvUtil
import com.tfh.library_common.config.AppConstants

object CacheUtil {

    fun getLoginToken(): String {
        return ""
    }

    fun checkLogin(): Boolean {
        return !KvUtil.decodeString(AppConstants.KvKey.LOGIN_ACCOUNT).isNullOrBlank()
    }

}