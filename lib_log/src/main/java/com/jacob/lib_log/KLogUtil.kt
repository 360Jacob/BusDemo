package com.jacob.lib_log

import android.text.TextUtils
import android.util.Log


object KLogUtil {

    fun isEmpty(line: String): Boolean {
        return TextUtils.isEmpty(line) || line == "\n" || line == "\t" || TextUtils.isEmpty(line.trim { it <= ' ' })
    }

    fun printLine(tag: String, isTop: Boolean) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════")
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════")
        }
    }

}
