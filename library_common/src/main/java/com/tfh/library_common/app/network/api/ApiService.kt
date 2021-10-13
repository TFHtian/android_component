package com.tfh.library_common.app.network.api

import retrofit2.http.*

interface ApiService {

    /***
     * 获取登录短信验证码
     * POST /login/getAuthCode
     */
    @Headers("Content-type:application/json")
    @POST("login/getAuthCode")
    suspend fun getAuthCode(
        @FieldMap map: Map<String, @JvmSuppressWildcards String>
    ): ApiResponse<Any?>

}