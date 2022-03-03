package com.jacob.lib_data_service.error.mapper

import com.jacob.lib_data_service.error.Error

interface ErrorFactory {
    fun getError(errorCode: Int): Error
}
