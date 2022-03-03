//package com.jacob.lib_data_service
//
//import com.jacob.lib_data_service.config.NetAppContext
//import com.jacob.lib_data_service.dto.Resource
//import com.jacob.lib_data_service.local.LocalData
//import com.jacob.lib_data_service.remote.RetrofitManager
//import com.jacob.lib_data_service.utils.NetworkHelper
//import com.jacob.lib_domain.Demo
//import com.jacob.lib_domain.remote.RemoteData
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.flow.flowOn
//import kotlin.coroutines.CoroutineContext
//
//
///**
// * 数据仓库进行分发
// * * 服务端
// * * 本地
// */
//class DataRepository constructor(
//    private val remoteRepository: RemoteData = RemoteData(
//        RetrofitManager(), NetworkHelper(NetAppContext.getContext())
//    ),
//    private val localRepository: LocalData = LocalData(),
//    private val ioDispatcher: CoroutineContext = Dispatchers.IO
//) :
//    DataRepositorySource {
//
//
//    override suspend fun requestRecipes(): Flow<Resource<List<Demo>>> {
//        return dealDataFlow { remoteRepository.requestRecipes() }
//    }
//
//
//    private inline fun <reified T> dealDataFlow(crossinline block: suspend () -> T): Flow<T> {
//        return flow {
//            emit(block.invoke())
//        }.flowOn(ioDispatcher)
//    }
//
//}
