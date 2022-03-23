package com.jacob.lib_data_service.remote.test

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.RetrofitManager
import com.jacob.lib_data_service.remote.service.RecipesService
import com.jacob.lib_domain.Demo

class LoginApi: BaseApi<Demo>() {


    override suspend fun loadData(params: HashMap<String, String>): Resource<Demo> {
        var service = RetrofitManager().create<RecipesService>()
        return dealDataWhen(processCall { service.login(params) })
    }

}