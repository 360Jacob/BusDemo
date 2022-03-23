package com.jacob.lib_data_service.remote.home.service

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface HomeService {
    @POST("pub/app/operate/homePageBizData")
    suspend fun queryHomePageBizData(@QueryMap params: HashMap<String, String>): Resource<HomePageDataWrapperVo>
}