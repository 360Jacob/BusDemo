package com.jacob.lib_data_service.remote.test

import android.text.TextUtils
import com.jacob.lib_data.BuildConfig
import com.jacob.lib_data_service.config.NetAppContext
import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.error.*
import com.jacob.lib_data_service.error.mapper.ErrorManager
import com.jacob.lib_data_service.error.mapper.ErrorMapper
import com.jacob.lib_data_service.remote.service.RecipesService
import com.jacob.lib_data_service.utils.NetworkHelper
import com.jacob.lib_data_service.utils.ThreadUtils
import com.jacob.lib_data_service.utils.ext.view.showToast
import com.jacob.lib_domain.base.BaseResponse
import com.jacob.lib_log.KLog
import retrofit2.Response
import java.io.IOException


abstract class BaseApi<T> {
   var networkConnectivity = NetworkHelper(NetAppContext.getContext())
   private val errorManager by lazy { ErrorManager(ErrorMapper()) }
    lateinit var service: RecipesService
    init {
        createService()
    }
   abstract fun createService()
    abstract suspend fun loadData(params: MutableMap<String, String?>): Resource<T>

   /**
    * 数据结构体的返回处理
    */
    suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
      if (!networkConnectivity.isConnected()) {
         //若当前客户端未打开数据连接开关
         return showToast(NOT_NETWORD)
      }
      return try {
         val response = responseCall.invoke()
         if (response.code() in SUCCESS until UNAUTHORIZED) {
            response.body()
         } else {
            when (response.code()) {
               UNAUTHORIZED -> showToast(UNAUTHORIZED)
               FORBIDDEN -> showToast(FORBIDDEN)
               NOT_FOUND -> showToast(NOT_FOUND)
               REQUEST_TIMEOUT -> showToast(REQUEST_TIMEOUT)
               INTERNAL_SERVER_ERROR -> showToast(INTERNAL_SERVER_ERROR)
               SERVICE_UNAVAILABLE -> showToast(SERVICE_UNAVAILABLE)
               else -> showToast(UNKNOWN)
            }
         }
      } catch (e: IOException) {
         if (BuildConfig.DEBUG) {
            ThreadUtils.runOnUiThread {
               e.message?.showToast()
            }
            KLog.e("RemoteData", e)
         }
         showToast(NETWORD_ERROR)
      }
   }

   /**
    * 处理相应结果
    */
    inline fun <reified T> dealDataWhen(any: Any?): Resource<T> {
      return when (any) {
         is BaseResponse<*> -> {
            Resource.Success(data = toAs(if (any.data != null) any.data else any.msg))
         }
         else -> {
            Resource.DataError(errorCode = toAs(any))
         }
      }
   }

   /**
    * 类型转换
    */
    inline fun <reified T> toAs(obj: Any?): T {
      return obj as T
   }

   /**
    * 错误吐司
    */
    fun showToast(code: Int, msg: String? = ""): Int {
      ThreadUtils.runOnUiThread {
         if (!TextUtils.isEmpty(msg)) msg?.showToast(NetAppContext.getContext())
         else errorManager.getError(code).description.showToast(NetAppContext.getContext())
      }
      return code
   }
}