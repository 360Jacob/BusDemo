package com.jacob.lib_data_service.remote.home.repository

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    suspend fun queryHomePageBizData(): Flow<Resource<HomePageDataWrapperVo>>
}