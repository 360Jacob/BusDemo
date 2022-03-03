package com.jacob.lib_base_module.event

/**
 * KeyCode
 *
 */
interface KeyCode {
    interface Main

    interface News {
        companion object {
            val NEWS_TYPE = "newstype"
            val NEWS_ID = "newsid"
        }
    }

    interface Find

    interface Me
}
