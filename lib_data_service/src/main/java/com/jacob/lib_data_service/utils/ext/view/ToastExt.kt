package com.jacob.lib_data_service.utils.ext.view

import android.content.Context
import android.widget.Toast
import com.jacob.lib_data_service.config.NetAppContext

/**
 * Describe:
 * <p></p>
 *
 * @author zhouhuan
 * @Date 2020/12/7
 */


/**
 * 使用方式
 * "This is Toast".showToast(context,Toast.LENGTH_SHORT)
 */
fun String.showToast(context: Context = NetAppContext.getContext(), duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

/**
 * 使用方式
 * "This is Toast".showToast(context,Toast.LENGTH_SHORT)
 */
fun Int.showToast(context: Context = NetAppContext.getContext(), duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}
