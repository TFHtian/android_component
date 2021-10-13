package com.tfh.library_common.app.network.api

object UrlManger {

    //测试环境
    private const val SERVER_URL_DEV = "http://47.101.190.40:8731/"
    //正式环境
    private const val SERVER_URL = "http://smarttiger.kissabc.com/st-course/"

    var isDevelop = false

    fun getServerUrl(): String {
        if (isDevelop) {
            return SERVER_URL_DEV
        }
        return SERVER_URL
    }

}