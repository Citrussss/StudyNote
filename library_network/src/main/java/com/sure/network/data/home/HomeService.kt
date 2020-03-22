package com.sure.network.data.home

import com.sure.network.RetrofitClient
import com.sure.network.constant.NetConstant.BASE_URL
import com.sure.network.data.api.model.Base
import com.sure.network.data.api.model.Home
import com.sure.network.data.api.model.Page
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * author pisa
 * date  2020/3/22
 * version 1.0
 * effect :首页接口
 */
interface HomeService {
    @GET(BASE_URL + "article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): Response<Base<Page<Home>>>

    companion object {
        val provideHomeService: HomeService =
            RetrofitClient.providerRetrofit().create(HomeService::class.java)
    }
}