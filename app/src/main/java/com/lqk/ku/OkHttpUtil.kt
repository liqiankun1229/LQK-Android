package com.lqk.ku

import android.util.Log
import com.google.gson.Gson
import com.lqk.network.thread.Action
import com.lqk.network.thread.Run
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * @author LQK
 * @time 2019/4/14 14:17
 * @remark OkHttp
 */
class OkHttpUtil : NetWorkOperation {

    companion object {
        const val TIME_OUT = 30L

        val instance: OkHttpUtil by lazy { OkHttpUtil() }
    }

    private val okHttpClient: OkHttpClient

    init {

        val okHttpClientBuilder = OkHttpClient.Builder()
        // 设置连接超时时间
        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        // 写入超时时间
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        // 读取超时时间
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
        // 设置重定向 默认 true
        okHttpClientBuilder.followRedirects(true)
        // 添加请求头
        okHttpClientBuilder.addInterceptor { chain ->
            run {
                val request = chain.request()
                        .newBuilder()
                        .addHeader("User-Agent", "Android-Mobile")
                        .build()
                return@run chain.proceed(request)
            }
        }
        // 添加 https 支持
        okHttpClientBuilder.hostnameVerifier(HostnameVerifier { hostname, session ->
            run {
                return@run true
            }
        })
        okHttpClientBuilder.sslSocketFactory(initSSLSockFactory(), initX509TrustManager())
        // 配置客户端
        okHttpClient = okHttpClientBuilder.build()
    }

    private fun initSSLSockFactory(): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("SSL")
        try {
            val x509TrustManager = arrayOf(initX509TrustManager())
            sslContext.init(null, x509TrustManager, SecureRandom())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sslContext.socketFactory
    }

    private fun initX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {

            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                Log.d("", "")
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                Log.d("", "")
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
    }

    private fun doGetRequest(url: String, params: HashMap<String, Any>): Request {
        var paramsUrl = "?"
        for (entry in params) {
            paramsUrl = "$paramsUrl${entry.key}=${entry.value}"
        }
        if (paramsUrl != "?") {
            paramsUrl = "$url$paramsUrl"
        }
        return Request.Builder()
                .url(paramsUrl)
                .get()
                .build()
    }

    private fun <T> doPostRequest(url: String, params: T): Request {

        val mediaType: MediaType = "application/json".toMediaType()
        val jsonObject = Gson().toJson(params)
        val requestBody = jsonObject.toRequestBody(mediaType)

        return Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
    }

    private fun doPostRequest(url: String, params: HashMap<String, Any>): Request {
        val mediaType: MediaType = "application/json".toMediaType()
        val jsonObject = JSONObject()
        for (entry in params) {
            jsonObject.put(entry.key, entry.value)
        }
        val requestBody = jsonObject.toString().toRequestBody(mediaType)
        return Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
    }

    private fun doCall(request: Request): Call {
        return okHttpClient.newCall(request)
    }

    override fun <T> doGet(url: String, params: HashMap<String, Any>, resultClass: Class<T>, callback: NetWorkCallback<T>?) {
        doCall(doGetRequest(url, params)).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback?.onFailed("网络请求失败")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.body != null) {
                    val data = response.body?.string()
                    if (data == null) {
                        callback?.onFailed("数据获取失败")
                    } else {
                        val resultData = Gson().fromJson<T>(data, resultClass) as T
                        Run.onUiAsync(object : Action {
                            override fun call() {
                                callback?.onSucceed(resultData)
                            }
                        })
                    }
                }
            }
        })
    }

//    fun <T> doPost(url: String, jsonParams: T, resultClass: Class<T>, callback: NetWorkCallback<T>?) {
//        doCall(doPostRequest(url, jsonParams)).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                callback?.onFailed("网络请求失败")
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                if (response.body != null) {
//                    val data = response.body?.string()
//                    if (data == null) {
//                        callback?.onFailed("数据获取失败")
//                    } else {
//                        val resultData = Gson().fromJson(data, resultClass) as T
//                        Run.onUiAsync(object : Action {
//                            override fun call() {
//                                callback?.onSucceed(resultData)
//                            }
//                        })
//                    }
//                }
//            }
//        })
//    }

    override fun <T> doPost(url: String, params: HashMap<String, Any>, resultClass: Class<T>, callback: NetWorkCallback<T>?) {
        doCall(doPostRequest(url, params)).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback?.onFailed("网络请求失败:${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.body != null) {
                    val data = response.body?.string()
                    if (data == null) {
                        callback?.onFailed("数据获取失败")
                    } else {
//                        val typeToken = object : TypeToken<T>() {}.rawType
//                        val resultData = Gson().fromJson(data, typeToken) as T
                        val resultData = Gson().fromJson(data, resultClass) as T
                        Run.onUiAsync(object : Action {
                            override fun call() {
                                callback?.onSucceed(resultData)
                            }
                        })
                    }
                }
            }
        })
    }

    override fun doPut() {
    }

    override fun doDelete() {
    }
}