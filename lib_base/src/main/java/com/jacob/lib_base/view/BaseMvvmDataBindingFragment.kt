package com.jacob.lib_base.view

import android.view.ViewStub
import androidx.databinding.ViewDataBinding
import com.jacob.lib_base.mvvm.viewmodel.BaseViewModel
import com.jacob.lib_base.view.databinding.FragmentBinding
import com.jacob.lib_base.view.databinding.FragmentBindingHolder

/**
 * Describe:
 * 基础 DataBinding 页面
 *
 */
abstract class BaseMvvmDataBindingFragment<V : ViewDataBinding, VM : BaseViewModel> :
    BaseMvvmFragment<VM>(), FragmentBindingHolder<V> by FragmentBinding() {

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
