package com.example.nytcleanarcitecture.domain.repository

import com.example.nytcleanarcitecture.domain.entity.Article
import kotlinx.coroutines.flow.Flow

interface MostPopularArticlesRepository {
    fun getArticles(period: Int): Flow<List<Article>>
}
