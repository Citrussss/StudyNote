package com.sure.network.constant

import android.content.pm.PackageManager
import com.sure.network.util.NetUtils

/**
 * author pisa
 * date  2020/3/21
 * version 1.0
 * effect :数据持久区
 */
object NetConstant {
    val BASE_URL: String by lazy {
        NetUtils.app?.apply {
            val applicationInfo = this.packageManager.getApplicationInfo(
                this.packageName, PackageManager.GET_META_DATA
            );
            if (applicationInfo.metaData != null) {
                return@lazy applicationInfo.metaData.get("SURE_BASE_URL").toString();
            }
        }
        return@lazy ""
    }
}