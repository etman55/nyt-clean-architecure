package com.example.domain.feature

import com.example.domain.base.coroutines.CoroutineDispatcherProvider
import com.example.nytcleanarcitecture.domain.base.usecase.FlowInteractor
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.repository.MostPopularArticlesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class MostPopularArticlesUseCase @Inject constructor(
    private val repository: MostPopularArticlesRepository,
    dispatcher: CoroutineDispatcherProvider
) : FlowInteractor<MostPopularArticlesUseCase.Params, List<Article>>(dispatcher) {

    override fun execute(params: Params?): Flow<List<Article>> {
        requireNotNull(params)
        return repository.getArticles(params.period)
    }

    data class Params(val period: Int)
}
