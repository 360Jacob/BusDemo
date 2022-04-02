package com.jacob.module_main

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.remote.RetrofitManager
import com.jacob.lib_data_service.remote.home.service.HomeService
import com.jacob.lib_data_service.utils.ext.launch
import com.jacob.lib_domain.base.BaseResp
import com.jacob.lib_domain.base.BaseResponse
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import com.jacob.module_main.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

//
//    override fun onBindLayout(): Int = R.layout.activity_main
//
//    override fun initView() {
//
//    }
//
//    override fun initData() {
//        mViewModel.queryHomePageBizData()
//    }
//
//    override fun onBindViewModel(): Class<MainViewModel> = MainViewModel::class.java
//
//    override fun initViewObservable() {
//        observe(mViewModel.homePageDataWrapperLiveData, ::handleHomePageData)
//    }
//
//    override fun onBindVariableId(): MutableList<Pair<Int, Any>> {
//        return arrayListOf(BR._all to mViewModel)
//    }

    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        var tvTitle = findViewById<TextView>(R.id.tv_title)
        tvTitle.setOnClickListener {
//            mViewModel.queryHomePageBizData()
            GlobalScope.launch {
                flow {
                    var temp = RetrofitManager().create(HomeService::class.java)
                        .queryHomePageBizData1(HashMap())
                    emit(temp)
                }.flowOn(Dispatchers.IO)  //指定网络请求的线程
                    .collect {
                        var tempStr = Gson().toJson(it?.response)
                        Log.e("MainActivity", "--->" + tempStr)

                    }
            }
        }

    }
    private fun handleHomePageData(resource: Resource<HomePageDataWrapperVo>) {
//        resource.launch {
//            it?.apply {
//                it.appBizTypeList?.get(0)?.bizTypeName?.let { it1 -> KLog.e(it1) }
//            }
//        }
    }

}

