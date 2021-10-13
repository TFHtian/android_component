package com.tfh.library_common.app.network.interceptor

import com.tfh.library_common.util.CacheUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class ParameterInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val token = CacheUtil.getLoginToken()
        //统一参数
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("Token", token)
            .build()
        val requestBuilder = original.newBuilder()
            .url(url)
            .method(original.method, original.body)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}