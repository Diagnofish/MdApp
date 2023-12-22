package com.example.diagnofish.service

import android.util.Log
import android.webkit.CookieManager
import com.example.diagnofish.model.DetectionDetail
import com.example.diagnofish.model.DetectionHistory
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiService {
    @POST("user/login")
    suspend fun login(
        @Body requestBody: RequestBody
    ): Map<String, String>

    @POST("user/register")
    suspend fun register(
        @Body requestBody: RequestBody
    ): Map<String, String>

    @Multipart
    @POST("detection")
    suspend fun detection(@Part image: MultipartBody.Part): DetectionHistory

    @GET("detection/history")
    suspend fun detectionHistory(): List<DetectionHistory>

    @GET("detection/history/{id}")
    suspend fun detectionDetail(@Path("id") id: String): DetectionDetail

    companion object {
        private var retrofitService: ApiService? = null
        fun getInstance(): ApiService {
            if (retrofitService == null) {
                var cookieManager: CookieManager
                var client = OkHttpClient
                    .Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .cookieJar(
                        object : CookieJar {

                            /**
                             * @param url
                             * @param cookies list of cookies get in api response
                             */
                            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {

                                cookieManager = CookieManager.getInstance()
                                for (cookie in cookies) {
                                    cookieManager.setCookie(url.host, cookie.toString())
                                    Log.e(
                                        "ok",
                                        "saveFromResponse :  Cookie url : " + url.host + " " + cookie.toString()
                                    )
                                }
                            }

                            /**
                             * @param url
                             *
                             * adding cookies with request
                             */
                            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                                cookieManager = CookieManager.getInstance()

                                val cookies: ArrayList<Cookie> = ArrayList()
                                if (cookieManager.getCookie(url.host) != null) {
                                    val splitCookies =
                                        cookieManager.getCookie(url.host).split("[,;]".toRegex())
                                            .dropLastWhile { it.isEmpty() }.toTypedArray()
                                    for (i in splitCookies.indices) {
                                        cookies.add(Cookie.parse(url, splitCookies[i].trim { it <= ' ' })!!)
                                        Log.e(
                                            "ok",
                                            "loadForRequest :Cookie.add ::  " + Cookie.parse(
                                                url,
                                                splitCookies[i].trim { it <= ' ' })!!
                                        )
                                    }
                                }
                                return cookies
                            }
                        }
                    )
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://diagnofish-api-hnobhrzdiq-et.a.run.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }
}