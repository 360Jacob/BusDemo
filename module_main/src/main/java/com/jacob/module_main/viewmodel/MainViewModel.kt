package com.jacob.module_main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jacob.lib_base.mvvm.viewmodel.BaseViewModel
import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.home.repository.HomeRepository
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {
    private val homePageDataWrapperLiveDataPrivate =
        MutableLiveData<Resource<HomePageDataWrapperVo>>()
    val homePageDataWrapperLiveData: LiveData<Resource<HomePageDataWrapperVo>> get() = homePageDataWrapperLiveDataPrivate
    val homeRepository: HomeRepository = HomeRepository()
    fun queryHomePageBizData() {
        viewModelScope.launch {
            homeRepository.queryHomePageBizData()
                .collect {
                    homePageDataWrapperLiveDataPrivate.value = it
                }
        }
    }
}