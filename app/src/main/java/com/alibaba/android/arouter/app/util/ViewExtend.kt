package com.alibaba.android.arouter.app.util

import android.view.View

private const val NO_ID = "NO_ID"

fun View.getEntryName(): String {
    return try {
        if (id == View.NO_ID) NO_ID
        else resources.getResourceEntryName(id)
    } catch (e: Exception) {
        NO_ID
    }
}

fun Any.getRadix16Code(): String = "0x${hashCode().toString(16).uppercase()}"