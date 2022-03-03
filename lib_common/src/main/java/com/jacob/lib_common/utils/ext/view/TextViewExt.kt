package com.jacob.lib_common.utils.ext.view

import android.widget.TextView

/**
 * Describe:
 * <p></p>
 *
 */

/**
 * if [TextView.getText] is not empty, invoke f()
 * otherwise invoke t()
 */
fun TextView.notEmpty(f: TextView.() -> Unit, t: TextView.() -> Unit) {
    if (text.toString().isNotEmpty()) f() else t()
}