package com.jacob.lib_data_service.remote.home.service

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_domain.base.BaseResp
import com.jacob.lib_domain.base.BaseResponse
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import com.jacob.lib_domain.entity.PrivateVersionVo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface HomeService {
    @POST("pub/app/operate/homePageBizData")
    suspend fun queryHomePageBizData(@QueryMap params: HashMap<String, String>): Response<Resource<HomePageDataWrapperVo>>

    @POST("pub/app/operate/homePageBizData")
    suspend fun queryHomePageBizData1(@QueryMap params: HashMap<String, String>): BaseResp<BaseResponse<HomePageDataWrapperVo>>

    /**
     * 获取隐私协议版本号
     */
    @POST("pub/operate/privacyInit")
    suspend fun privacyInit(@QueryMap params: HashMap<String, String>): Response<Resource<PrivateVersionVo>>

}