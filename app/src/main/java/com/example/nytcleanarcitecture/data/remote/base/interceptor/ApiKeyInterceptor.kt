package com.example.nytcleanarcitecture.data.remote.base.interceptor

import com.example.nytcleanarcitecture.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api-key", BuildConfig.API_KEY)
            .build()
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
