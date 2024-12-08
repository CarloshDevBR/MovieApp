package com.example.movietmdbapp.core.data.remote

import com.example.movietmdbapp.core.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ReqInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url
            .newBuilder()
            .addQueryParameter(Constants.LANGUAGE_PARAM, Constants.LANGUAGE_VALUE)
            .build()

        val newRequest = request
            .newBuilder()
            .addHeader(Constants.API_KEY_HEADER, Constants.API_KEY_VALUE)
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }
}