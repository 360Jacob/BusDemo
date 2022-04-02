package com.jacob.lib_data_service.remote

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_domain.Demo
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import kotlinx.coroutines.flow.Flow

/**
 * 服务端所有提供数据方法
 */
internal interface RemoteDataSource {
    suspend fun queryHomePageBizData(): Flow<Resource<HomePageDataWrapperVo>>
}
