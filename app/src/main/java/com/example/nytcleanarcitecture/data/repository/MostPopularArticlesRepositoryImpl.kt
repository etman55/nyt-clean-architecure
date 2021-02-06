package com.example.nytcleanarcitecture.data.repository

import com.example.nytcleanarcitecture.data.feature.MostPopularArticlesRemoteDataSource
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.mapper.ArticlesRemoteMapper
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.repository.MostPopularArticlesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MostPopularArticlesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MostPopularArticlesRemoteDataSource,
    private val remoteMapper: ArticlesRemoteMapper
) : MostPopularArticlesRepository {

    override fun getArticles(period: Int): Flow<List<Article>> {
        return flow {
            val response = remoteDataSource.getMostPopularArticlesByPeriod(period)
            emit(remoteMapper.mapModelList(response.results))
        }
    }
}
