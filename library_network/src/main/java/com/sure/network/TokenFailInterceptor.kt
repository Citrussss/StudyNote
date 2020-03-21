//package com.sure.network
//
//import okhttp3.Interceptor
//import okhttp3.Response
//import org.json.JSONException
//import org.json.JSONObject
//import java.io.IOException
//import java.nio.charset.Charset
//
///**
// * author : kele
// * date :  2020/3/19 9:54
// * description :
// */
//class TokenFailInterceptor : Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        val originalResponse = chain.proceed(request)
//        val responseBody = originalResponse.body()
//        val source = responseBody!!.source()
//        source.request(Long.MAX_VALUE)
//        val buffer = source.buffer()
//        var charset = Charset.forName("UTF-8")
//        val contentType = responseBody.contentType()
//        if (contentType != null) {
//            charset = contentType.charset(Charset.forName("UTF-8"))
//        }
//        val bodyString = buffer.clone().readString(charset)
//        try {
//            val jsonObject = JSONObject(bodyString)
//            val status = jsonObject.optInt("status")
//            //  判断返回的状态码和后台约定好的Token过期状态码一致，就跳转到登录页面
///*if (status == 405) {
//                WeakReference<Context> weakReference = MyApplication.mWeakReference;
//                Context context = weakReference.get();
//                ActivityUtils.getInstance().restartLogin(context);
//            }*/
//        } catch (e: JSONException) {
//            e.printStackTrace()
//        }
//        return originalResponse
//    }
//}