package com.jacob.lib_data_service.remote.test

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.RetrofitManager
import com.jacob.lib_data_service.remote.service.RecipesService
import com.jacob.lib_domain.Demo

class LoginApi: BaseApi<Demo>() {


    init {
        createService()
    }


    override fun createService() {
        service =  RetrofitManager().create<RecipesService>()
    }

    override suspend fun loadData(params: MutableMap<String, String?>): Resource<Demo> {

        return dealDataWhen(processCall {  service.login(params) })
    }

}