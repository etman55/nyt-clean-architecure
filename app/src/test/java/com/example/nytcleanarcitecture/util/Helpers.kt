package com.example.nytcleanarcitecture.util

import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.service.ApiHandler
import com.google.common.io.Resources
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.net.URL
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val REQUEST_PATH: String = "/mostpopular/v2/viewed/7.json"

private val okHttpClient: OkHttpClient
    get() = OkHttpClient.Builder().build()

private val gson: Gson
    get() = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create()

@Suppress("UnstableApiUsage")
internal fun getJson(path: String): String {
    val uri: URL = Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}

internal fun makeTestApiService(mockWebServer: MockWebServer): ApiHandler = Retrofit.Builder()
    .baseUrl(mockWebServer.url("/"))
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()
    .create(ApiHandler::class.java)
