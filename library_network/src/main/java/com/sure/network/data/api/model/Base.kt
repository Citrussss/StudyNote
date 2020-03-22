package com.sure.network.data.api.model

/**
 * author pisa
 * date  2020/3/22
 * version 1.0
 * effect :基础model
 */
class Base<T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String
) {
}