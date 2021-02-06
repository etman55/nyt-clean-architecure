package com.example.nytcleanarcitecture.domain.feature

import com.example.nytcleanarcitecture.domain.base.coroutines.CoroutineDispatcherProvider
import com.example.nytcleanarcitecture.domain.base.usecase.FlowInteractor
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.fake.MostPopularArticlesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticleByIdUseCase @Inject constructor(
    private val repository: MostPopularArticlesRepository,
    dispatcher: CoroutineDispatcherProvider
) : FlowInteractor<ArticleByIdUseCase.Params, Article>(dispatcher) {
    override fun execute(params: Params?): Flow<Article> {
        requireNotNull(params)
        return repository.getArticles(params.period).map {
            return@map it.first { article ->
                article.id == params.articleId
            }
        }
    }

    data class Params(val period: Int, val articleId: Long)
}
