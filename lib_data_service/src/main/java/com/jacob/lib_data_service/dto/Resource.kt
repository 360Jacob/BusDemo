package com.jacob.lib_data_service.dto

import com.jacob.lib_data_service.error.SUCCESS
import com.jacob.lib_domain.entity.HomePageDataWrapperVo


// A generic class that contains data and status about loading this data.
sealed class Resource<out T>(
    val data: T? = null,
    val msg: String = "",
    val code: Int = SUCCESS
) {
    class Success<out T>(data: T, msg: String) : Resource<T>(data, msg)
    class Loading<out T>(data: T? = null) : Resource<T>(data)
    class DataError<out T>(code: Int, msg: String) : Resource<T>(null, msg, code)

    fun isSuccess(): Boolean {
        return code == SUCCESS
    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$code]"
            is Loading<T> -> "Loading"
        }
    }
}
