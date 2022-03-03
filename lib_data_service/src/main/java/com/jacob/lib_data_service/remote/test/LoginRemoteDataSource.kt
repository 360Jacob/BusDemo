package com.jacob.lib_data_service.remote.test

import com.jacob.lib_data_service.dto.Resource

/**
 * 用户登录信息--服务端数据源
 */
interface LoginRemoteDataSource {
    fun login(): Resource<LoginInfoBean>
}