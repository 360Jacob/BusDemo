package com.jacob.lib_data_service.remote.home.service

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.base.BaseApi
import com.jacob.lib_domain.entity.HomePageDataWrapperVo

class QueryHomePageBizDataApi : BaseApi<HomePageDataWrapperVo>() {

    override suspend fun loadData(params: HashMap<String, String>): Resource<HomePageDataWrapperVo> {
        return dealDataWhen(processCall {
            retrofit.create<HomeService>().queryHomePageBizData(params)
        })
    }

}