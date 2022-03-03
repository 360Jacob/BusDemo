package com.jacob.lib_data_service

import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_domain.Demo
import kotlinx.coroutines.flow.Flow


/**
 *
 */
interface DataRepositorySource {
    suspend fun requestRecipes(): Flow<Resource<List<Demo>>>
//    suspend fun doLogin(): Flow<Resource<String>>
//    suspend fun removeUserTestRoom(userTestRoom: UserTestRoom): Flow<Resource<Int>>
//    suspend fun insertUserTestRoom(userTestRoom: UserTestRoom):Flow<Resource<Long>>
//    suspend fun getAllUserTestRoom(): Flow<Resource<List<UserTestRoom>>>
}
