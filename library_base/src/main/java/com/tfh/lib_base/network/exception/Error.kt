package com.tfh.lib_base.network.exception

enum class Error(private val code: Int, private val err: String) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, "请求失败，请稍后再试"),

    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "解析错误，请稍后再试"),

    /**
     * 网络错误
     */
    NETWORK_ERROR(1002, "网络连接错误，请稍后重试"),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, "证书出错，请稍后再试"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, "网络连接超时，请稍后重试"),

    /***
     * token过期
     * 暂未登录或token已经过期
     * @return String
     */
    TOKEN_ERROR(1007, "您的账号在其他设备登录,请确认是否为本人操作");


    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}