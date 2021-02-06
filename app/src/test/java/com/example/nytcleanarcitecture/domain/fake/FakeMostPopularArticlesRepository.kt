package com.example.nytcleanarcitecture.domain.fake

import com.example.nytcleanarcitecture.domain.data.DummyData
import com.example.nytcleanarcitecture.domain.entity.Article
import java.net.SocketTimeoutException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

internal class FakeMostPopularArticlesRepository : MostPopularArticlesRepository {

    companion object {
        const val ERROR_MSG: String = "No network"
    }

    private var articlesFlow: Flow<List<Article>> = flowOf(listOf(DummyData.article))

    override fun getArticles(period: Int): Flow<List<Article>> {
        return articlesFlow
    }

    var responseType: ResponseType = ResponseType.DATA
        set(value) {
            field = value
            articlesFlow = makeResponse(value)
        }

    private fun makeResponse(type: ResponseType): Flow<List<Article>> {
        return when (type) {
            ResponseType.DATA -> flowOf(listOf(DummyData.article))
            ResponseType.EMPTY -> flowOf(listOf())
            ResponseType.ERROR -> flow { throw SocketTimeoutException(ERROR_MSG) }
        }
    }
}

internal enum class ResponseType {
    DATA,
    EMPTY,
    ERROR
}
