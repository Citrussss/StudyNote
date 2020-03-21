package com.sure.network

import com.sure.network.constant.NetConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset

class RetrofitClient private constructor() {
    private var mApiService: ApiService? = null
    //    private Interceptor getHeaderInterceptor() {
//        return new Interceptor() {
//            @Override
//            public Response intercept(@NonNull Chain chain) throws IOException {
//                WeakReference<Context> weakReference = MyApplication.mWeakReference;
//                Context context = weakReference.get();
//                String phone = (String) SPUtils.get(context, Constant.KEY_USER_PHONE,
//                        "");
//                String token = (String) SPUtils.get(context, Constant.KEY_USER_TOKEN,
//                        "");
//                Request original = chain.request();
//                Request.Builder requestBuilder = original.newBuilder()
//                        //添加Header
//                        .header(Constant.KEY_HEADER_ACCESS_PHONE, phone)
//                        .header(Constant.KEY_HEADER_ACCESS_TOKEN, token);
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        };
//
//    }
    /**
     * 设置拦截器
     *
     * @return
     */
    private val mMessage = StringBuilder()

    // 请求或者响应开始
    // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
    // 响应结束，打印整条日志
    //显示日志
    private val interceptor: Interceptor
        private get() {
            val interceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message: String ->
                    // 请求或者响应开始
                    if (message.startsWith("--> POST")) {
                        mMessage.setLength(0)
                    }
                    // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化

                    mMessage.append(
                        if (message.startsWith("{") && message.endsWith("}")
                            || message.startsWith("[") && message.endsWith("]")
                        ) {
                            formatJson(
                                decodeUnicode(message)
                            )
                        } else {
                            message
                        } + "\n"
                    )
                    // 响应结束，打印整条日志
                    if (message.startsWith("<-- END HTTP")) {
                        HttpLoggingInterceptor.Logger.DEFAULT.log(mMessage.toString())
                    }
                })
            //显示日志
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

    /**
     * 获取接口服务类
     *
     * @return
     */
    val api: ApiService?
        get() {
            if (mApiService == null) {
                mApiService = buildApi()
            }
            return mApiService
        }

    /**
     * 创建接口类
     *
     * @return
     */
    private fun buildApi(): ApiService? { //初始化一个client,不然retrofit会自己默认添加一个
        val client = OkHttpClient().newBuilder() //设置Header
//                .addInterceptor(getHeaderInterceptor())
//设置拦截器
            .addNetworkInterceptor(interceptor) //                .addInterceptor(new TokenFailInterceptor())
            .build()
        val retrofit = Retrofit.Builder()
            .client(client) //设置网络请求的Url地址
            .baseUrl(NetConstant.BASE_URL) //设置数据解析器
            .addConverterFactory(GsonConverterFactory.create()) //设置网络请求适配器，使其支持RxJava与RxAndroid
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        //创建—— 网络请求接口—— 实例
        mApiService = retrofit.create(ApiService::class.java)
        return mApiService
    }

    companion object {
        @Volatile
        private var mInstance: RetrofitClient? = null

        val instance: RetrofitClient?
            get() {
                if (mInstance == null) {
                    synchronized(RetrofitClient::class.java) {
                        if (mInstance == null) {
                            mInstance = RetrofitClient()
                        }
                    }
                }
                return mInstance
            }

        /**
         * 设置Header
         *
         * @return
         */
        private val UTF8 = Charset.forName("UTF-8")

        /**
         * 格式化json字符串
         *
         * @param jsonStr 需要格式化的json串
         * @return 格式化后的json串
         */
        fun formatJson(jsonStr: String?): String {
            if (null == jsonStr || "" == jsonStr) return ""
            val sb = StringBuilder()
            var last = '\u0000'
            var current = '\u0000'
            var indent = 0
            for (i in 0 until jsonStr.length) {
                last = current
                current = jsonStr[i]
                when (current) {
                    '{', '[' -> {
                        sb.append(current)
                        sb.append('\n')
                        indent++
                        addIndentBlank(sb, indent)
                    }
                    '}', ']' -> {
                        sb.append('\n')
                        indent--
                        addIndentBlank(sb, indent)
                        sb.append(current)
                    }
                    ',' -> {
                        sb.append(current)
                        if (last != '\\') {
                            sb.append('\n')
                            addIndentBlank(sb, indent)
                        }
                    }
                    else -> sb.append(current)
                }
            }
            return sb.toString()
        }

        /**
         * http 请求数据返回 json 中中文字符为 unicode 编码转汉字转码
         *
         * @param theString
         * @return 转化后的结果.
         */
        fun decodeUnicode(theString: String): String {
            var aChar: Char
            val len = theString.length
            val outBuffer = StringBuffer(len)
            var x = 0
            while (x < len) {
                aChar = theString[x++]
                if (aChar == '\\') {
                    aChar = theString[x++]
                    if (aChar == 'u') {
                        var value = 0
                        for (i in 0..3) {
                            aChar = theString[x++]
                            value = when (aChar) {
                                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> (value shl 4) + aChar.toInt() - '0'.toInt()
                                'a', 'b', 'c', 'd', 'e', 'f' -> (value shl 4) + 10 + aChar.toInt() - 'a'.toInt()
                                'A', 'B', 'C', 'D', 'E', 'F' -> (value shl 4) + 10 + aChar.toInt() - 'A'.toInt()
                                else -> throw IllegalArgumentException(
                                    "Malformed   \\uxxxx   encoding."
                                )
                            }
                        }
                        outBuffer.append(value.toChar())
                    } else {
                        aChar = when (aChar) {
                            't' -> '\t'
                            'r' -> '\r'
                            'n' -> '\n'
                            'f' -> '\u000C'
                            else -> aChar
                        }
                        outBuffer.append(aChar)
                    }
                } else outBuffer.append(aChar)
            }
            return outBuffer.toString()
        }

        /**
         * 添加space
         *
         * @param sb
         * @param indent
         */
        private fun addIndentBlank(sb: StringBuilder, indent: Int) {
            for (i in 0 until indent) {
                sb.append('\t')
            }
        }
    }
}