package com.jacob.lib_base.view

import android.view.ViewStub
import androidx.databinding.ViewDataBinding
import com.jacob.lib_base.mvvm.viewmodel.BaseViewModel
import com.jacob.lib_base.view.databinding.ActivityBinding
import com.jacob.lib_base.view.databinding.ActivityBindingHolder

/**
 * Describe:
 * 基础 DataBinding 页面
 *
 */
abstract class BaseMvvmDataBindingActivity<V : ViewDataBinding, VM : BaseViewModel> :
    BaseMvvmActivity<VM>(), ActivityBindingHolder<V> by ActivityBinding() {

    override fun initContentView(mViewStubContent: ViewStub) {
        with(mViewStubContent) {
            layoutResource = onBindLayout()
            inflate(this) { binding ->
                onBindVariableId().forEach { pair ->
                    binding.setVariable(pair.first, pair.second)
                }
            }
        }
    }

    abstract fun onBindVariableId(): MutableList<Pair<Int, Any>>
}
