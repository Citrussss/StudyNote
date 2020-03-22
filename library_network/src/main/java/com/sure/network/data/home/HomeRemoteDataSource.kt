package com.sure.network.data.home

import com.sure.network.data.Result
import com.sure.network.data.api.model.Base
import com.sure.network.data.api.model.Home
import com.sure.network.data.api.model.Page
import retrofit2.Response
import java.io.IOException


/**
 * author pisa
 * date  2020/3/22
 * version 1.0
 * effect :
 */
class HomeRemoteDataSource(var service: HomeService = HomeService.provideHomeService) {

    suspend fun loadHomeMore(page: Int): Result<Page<Home>> {
        val response = service.getArticleList(page);
        return getResult(response, onError = {
            Result.Error(
                IOException("Error getting stories ${response.code()} ${response.message()}")
            )
        })
    }

    private inline fun getResult(
        response: Response<Base<Page<Home>>>,
        onError: () -> Result.Error
    ): Result<Page<Home>> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body.data)
            }
        }
        return onError.invoke()
    }
}