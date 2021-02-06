package com.example.nytcleanarcitecture.data.remote.feature.mostpopular

import com.example.nytcleanarcitecture.data.feature.MostPopularArticlesRemoteDataSource
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MostPopularArticlesResponse
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.service.ApiHandler
import javax.inject.Inject

class MostPopularArticlesRemoteDataSourceImpl @Inject constructor(
    private val apiHandler: ApiHandler
) : MostPopularArticlesRemoteDataSource {

    override suspend fun getMostPopularArticlesByPeriod(period: Int): MostPopularArticlesResponse {
        return apiHandler.mostPopularArticles(period)
    }
}
