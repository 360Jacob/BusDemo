package com.jacob.lib_data_service.remote.home.service

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.RetrofitManager
import com.jacob.lib_data_service.remote.test.BaseApi
import com.jacob.lib_domain.entity.PrivateVersionVo

class QueryPrivateApi : BaseApi<PrivateVersionVo>() {

    override suspend fun loadData(params: HashMap<String, String>): Resource<PrivateVersionVo> {
        return dealDataWhen(processCall {
            RetrofitManager().create<HomeService>().privacyInit(params)
        })
    }

}