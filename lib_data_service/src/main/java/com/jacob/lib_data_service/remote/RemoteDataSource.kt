package com.jacob.lib_data_service.remote

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_domain.Demo

/**
 * 服务端所有提供数据方法
 */
internal interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<List<Demo>>
}
