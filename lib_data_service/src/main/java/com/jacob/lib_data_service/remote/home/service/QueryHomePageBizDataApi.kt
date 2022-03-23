package com.jacob.lib_data_service.remote.home.service

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.RetrofitManager
import com.jacob.lib_data_service.remote.test.BaseApi
import com.jacob.lib_domain.entity.HomePageDataWrapperVo

class QueryHomePageBizDataApi : BaseApi<HomePageDataWrapperVo>() {

    override suspend fun loadData(params: HashMap<String, String>): Resource<HomePageDataWrapperVo> {
        var service = RetrofitManager().create<HomeService>()
        var reps = service.queryHomePageBizData(params)
        return dealDataWhen(reps)
    }
}