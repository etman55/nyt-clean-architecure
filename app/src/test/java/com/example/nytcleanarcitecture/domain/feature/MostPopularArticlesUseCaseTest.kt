package com.example.nytcleanarcitecture.domain.feature

import com.example.nytcleanarcitecture.domain.base.coroutines.CoroutineDispatcherProvider
import com.example.nytcleanarcitecture.domain.base.usecase.FlowInteractor
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.fake.FakeMostPopularArticlesRepository
import com.example.nytcleanarcitecture.domain.fake.MostPopularArticlesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before


@ExperimentalCoroutinesApi
class ArticleByIdUseCaseTest {
    private lateinit var testDispatcher: TestCoroutineDispatcher
    private lateinit var coroutinesDispatcherProvider: CoroutineDispatcherProvider
    private lateinit var fakeRepository: MostPopularArticlesRepository
    private lateinit var articlesByIdUseCase: FlowInteractor<ArticleByIdUseCase.Params, Article>

    @Before
    fun setUp() {
        testDispatcher = TestCoroutineDispatcher()
        coroutinesDispatcherProvider = CoroutineDispatcherProvider(
            io = testDispatcher,
            computation = testDispatcher,
            ui = testDispatcher
        )
        fakeRepository = FakeMostPopularArticlesRepository()
        articlesByIdUseCase = ArticleByIdUseCase(fakeRepository, coroutinesDispatcherProvider)
    }


}