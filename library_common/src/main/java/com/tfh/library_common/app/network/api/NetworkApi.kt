package com.tfh.library_common.app.network.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.Cache
import com.google.gson.GsonBuilder
import com.tfh.lib_base.ext.logi
import com.tfh.lib_base.network.BaseNetworkApi
import com.tfh.lib_base.network.interceptor.CacheInterceptor
import com.tfh.lib_base.network.interceptor.MyHeadInterceptor
import com.tfh.lib_base.network.interceptor.logging.LogInterceptor
import com.tfh.library_common.app.App
import com.tfh.library_common.app.network.interceptor.ParameterInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    NetworkApi.INSTANCE.getApi(ApiService::class.java, UrlManger.getServerUrl())
}

class NetworkApi : BaseNetworkApi() {

    companion object {
        val INSTANCE: NetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkApi()
        }
    }

    override fun setHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        "setHttpClientBuilder".logi("http")
        builder.apply {
            //设置缓存配置 缓存最大10M
            cache(
                    Cache(
                            File(App.instance.applicationContext.cacheDir, "cxk_cache"),
                            10 * 1024 * 1024
                    )
            )

            addInterceptor(ParameterInterceptor())

            //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
            addInterceptor(MyHeadInterceptor())
            //添加缓存拦截器 可传入缓存天数，不传默认7天
            addInterceptor(CacheInterceptor())
            // 日志拦截器
            addInterceptor(LogInterceptor())
            //超时时间 连接、读、写
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }
        return builder
    }

    override fun setRetrofitBuilder(builder: Retrofit.Builder): Retrofit.Builder {
        return builder.apply {
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        }
    }

}