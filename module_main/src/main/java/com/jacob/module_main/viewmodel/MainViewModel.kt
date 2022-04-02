package com.jacob.module_main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jacob.lib_base.mvvm.viewmodel.BaseViewModel
import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.home.repository.HomeRepository
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import com.jacob.lib_domain.entity.PrivateVersionVo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    private val homePageDataWrapperLiveDataPrivate =
        MutableLiveData<Resource<HomePageDataWrapperVo>>()
    private val homeRepository: HomeRepository = HomeRepository.instances
    fun queryHomePageBizData() {
        viewModelScope.launch {
            homeRepository.queryHomePageBizData()
                .collect {
                    Log.e(
                        "--MainViewModel->",
                        "" + it.code + "--msg->" + it.msg + "--advTitle->" + it.data?.bgAdList?.get(
                            0
                        )?.advTitle
                    )
                    homePageDataWrapperLiveDataPrivate.value = it
                }
        }
    }

    var privateLiveData = MutableLiveData<Resource<PrivateVersionVo>>()
    fun queryPrivate() {
        viewModelScope.launch {
            homeRepository.queryPrivate()
                .collect {
                    privateLiveData.value = it
                }
        }
    }
}