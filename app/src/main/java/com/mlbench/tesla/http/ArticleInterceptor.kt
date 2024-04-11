package com.mlbench.tesla.http

import com.google.gson.Gson
import com.mlbench.probau.utils.getToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class
ArticleInterceptor @Inject constructor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .addHeader("Accept", "application/json")
            .method(originalRequest.method, originalRequest.body)
        val gson = Gson()
        val json = gson.toJson(originalRequest)
        val check=json
        val token = getToken()
        requestBuilder.addHeader("Authorization", "Bearer $token")
        return chain.proceed(requestBuilder.build())

    }

}