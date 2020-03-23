package com.sure.network.data.home

import com.sure.network.data.Result
import com.sure.network.data.api.model.Home
import com.sure.network.data.api.model.Page
import java.lang.Exception

/**
 * author pisa
 * date  2020/3/22
 * version 1.0
 * effect :
 */
class HomeRepository(private val remoteDataSource: HomeRemoteDataSource = HomeRemoteDataSource()) {

    suspend fun loadStories(page: Int) = getData {
        remoteDataSource.loadHomeMore(page)
    }

    private suspend fun getData(
        request: suspend () -> Result<Page<Home>>
    ): Result<Page<Home>> {
        return request()
    }
}