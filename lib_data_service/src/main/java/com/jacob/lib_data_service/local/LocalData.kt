package com.jacob.lib_data_service.local

import com.jacob.lib_data_service.config.NetAppContext
import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.local.entity.UserTestRoom

/**
 * 本地数据
 */
class LocalData constructor() {

    private val appDatabase by lazy { AppDatabase.getDatabase(NetAppContext.getContext()) }

    fun getUserTestRoom(): Resource<List<UserTestRoom>> {
        return Resource.Success(appDatabase.userTestRoomDao().loadAllUserTestRooms(), "获取用户信息成功")
    }

    fun insertUserTestRoom(userTestRoom: UserTestRoom): Resource<Long> {
        return Resource.Success(
            appDatabase.userTestRoomDao().insertUserTestRoom(userTestRoom = userTestRoom),
            "新增数据成功"
        )
    }

    fun doLogin(): Resource<String> {
        return Resource.Success("String", "")
    }

    fun removeUserTestRoom(userTestRoom: UserTestRoom): Resource<Int> {
        return Resource.Success(
            appDatabase.userTestRoomDao().deleteUserTestRoom(userTestRoom),
            "测试删除数据成功"
        )
    }
}
