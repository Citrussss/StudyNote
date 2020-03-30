package com.sure.network

import android.util.Log
import com.sure.network.exception.ApiException

/**
 * author pisa
 * date  2020/3/30
 * version 1.0
 * effect :
 */
fun Exception.onError(): Exception? {
    val b = this is RuntimeException
    when (this::javaClass) {
        ApiException::javaClass -> {
            Log.i(this.javaClass.name, "ApiException")
        }
        else -> return this
    }
    return null
}