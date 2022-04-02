package com.jacob.lib_data_service.remote.home.repository

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.home.service.QueryHomePageBizDataApi
import com.jacob.lib_data_service.remote.home.service.QueryPrivateApi
import com.jacob.lib_data_service.remote.base.BaseRepository
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import com.jacob.lib_domain.entity.PrivateVersionVo
import kotlinx.coroutines.flow.Flow

class HomeRepository : BaseRepository(), IHomeRepository {
    companion object {
        val instances: HomeRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HomeRepository()
        }
    }

    override suspend fun queryHomePageBizData(): Flow<Resource<HomePageDataWrapperVo>> {
        return dealDataFlow {
            var params = HashMap<String, String>()
            QueryHomePageBizDataApi().loadData(params)
        }
    }

    suspend fun queryPrivate(): Flow<Resource<PrivateVersionVo>> {
        return dealDataFlow {
            var params = HashMap<String, String>()
            QueryPrivateApi().loadData(params)
        }
    }
}