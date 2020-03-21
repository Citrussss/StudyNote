package com.sure.network

import android.app.Application
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.sure.network.constant.NetConstant
import com.sure.network.util.NetUtils


/**
 * author pisa
 * date  2020/3/21
 * version 1.0
 * effect :网络工具库的初始化
 */
class NetworkProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        context?.applicationContext.apply {
            NetUtils.app = this as Application?
        }
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}