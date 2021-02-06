package com.example.nytcleanarcitecture.util

import java.net.HttpURLConnection
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ArticlesRequestDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            REQUEST_PATH -> MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(getJson("response/articles.json"))
            else -> throw IllegalArgumentException("Unknown Request Path ${request.path}")
        }
    }
}
