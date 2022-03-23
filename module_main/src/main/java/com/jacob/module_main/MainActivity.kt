package com.jacob.module_main

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.jacob.lib_base.view.BaseMvvmDataBindingActivity
import com.jacob.lib_data_service.dto.Resource
import com.jacob.lib_data_service.utils.ext.launch
import com.jacob.lib_data_service.utils.ext.observe
import com.jacob.lib_domain.entity.HomePageDataWrapperVo
import com.jacob.lib_log.KLog
import com.jacob.module_main.databinding.ActivityMainBinding
import com.jacob.module_main.viewmodel.MainViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.jacob.lib_data_service.remote.home.repository.HomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


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
//            GlobalScope.launch(){
//            HomeRepository().queryHomePageBizData()
//                .collect {
//                    it.data.apply {
//                        KLog.e(Gson().toJson(it))
//                    }
//                }
//        }
            mViewModel.queryHomePageBizData()
        }

    }

    private fun handleHomePageData(resource: Resource<HomePageDataWrapperVo>) {
        resource.launch {
            it?.apply {
                it.appBizTypeList?.get(0)?.bizTypeName?.let { it1 -> KLog.e(it1) }
            }
        }
    }

}

