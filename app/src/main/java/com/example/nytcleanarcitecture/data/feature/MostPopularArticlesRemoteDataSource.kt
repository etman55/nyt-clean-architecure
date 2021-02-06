package com.example.nytcleanarcitecture.data.feature

import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MostPopularArticlesResponse

interface MostPopularArticlesRemoteDataSource {

    suspend fun getMostPopularArticlesByPeriod(period: Int): MostPopularArticlesResponse
}
