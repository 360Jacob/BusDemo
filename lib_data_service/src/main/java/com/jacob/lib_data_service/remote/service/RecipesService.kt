package com.jacob.lib_data_service.remote.service

import com.jacob.lib_domain.Demo
import com.jacob.lib_domain.base.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * 服务端提供数据接口方法
 */
interface RecipesService {

    @GET("recipes.json")
    suspend fun login(@QueryMap originParams: MutableMap<String, String?>): Response<BaseResponse<Demo>>
    @GET("recipes.json")
    suspend fun logout(@QueryMap originParams: MutableMap<String, String?>): Response<BaseResponse<Demo>>
}