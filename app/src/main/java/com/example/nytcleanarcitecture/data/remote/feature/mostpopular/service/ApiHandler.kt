package com.example.nytcleanarcitecture.data.remote.feature.mostpopular.service

import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MostPopularArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiHandler {

    @GET("mostpopular/v2/viewed/{period}.json")
    suspend fun mostPopularArticles(@Path("period") period: Int): MostPopularArticlesResponse
}
