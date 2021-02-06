package com.example.nytcleanarcitecture.domain.feature

import com.example.nytcleanarcitecture.domain.base.coroutines.CoroutineDispatcherProvider
import com.example.nytcleanarcitecture.domain.base.usecase.FlowInteractor
import com.example.nytcleanarcitecture.domain.base.usecase.assertThrows
import com.example.nytcleanarcitecture.domain.data.DummyData
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.fake.FakeMostPopularArticlesRepository
import com.example.nytcleanarcitecture.domain.fake.ResponseType
import com.google.common.truth.Truth.assertThat
import java.net.SocketTimeoutException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleByIdUseCaseTest {
    private lateinit var testDispatcher: TestCoroutineDispatcher
    private lateinit var coroutinesDispatcherProvider: CoroutineDispatcherProvider
    private lateinit var fakeRepository: FakeMostPopularArticlesRepository
    private lateinit var articleUseCase: FlowInteractor<ArticleByIdUseCase.Params, Article>

    @Before
    fun setUp() {
        testDispatcher = TestCoroutineDispatcher()
        coroutinesDispatcherProvider = CoroutineDispatcherProvider(
            io = testDispatcher,
            computation = testDispatcher,
            ui = testDispatcher
        )
        fakeRepository = FakeMostPopularArticlesRepository()
        articleUseCase = ArticleByIdUseCase(fakeRepository, coroutinesDispatcherProvider)
    }

    @Test
    fun `check that calling getArticle by id returns article`() = testDispatcher.runBlockingTest {
        fakeRepository.responseType = ResponseType.DATA
        val article: Article = articleUseCase(ArticleByIdUseCase.Params(7, 123456)).first()
        assertThat(article).isEqualTo(DummyData.article)
    }

    @Test
    fun `check that calling getArticle by id returns correct article`() = testDispatcher.runBlockingTest {
        fakeRepository.responseType = ResponseType.DATA
        val article: Article = articleUseCase(ArticleByIdUseCase.Params(7, 123456)).first()
        assertThat(article.uri).isEqualTo(DummyData.article.uri)
        assertThat(article.url).isEqualTo(DummyData.article.url)
        assertThat(article.id).isEqualTo(DummyData.article.id)
        assertThat(article.assetId).isEqualTo(DummyData.article.assetId)
        assertThat(article.source).isEqualTo(DummyData.article.source)
        assertThat(article.publishedDate).isEqualTo(DummyData.article.publishedDate)
        assertThat(article.updated).isEqualTo(DummyData.article.updated)
        assertThat(article.section).isEqualTo(DummyData.article.section)
        assertThat(article.subsection).isEqualTo(DummyData.article.subsection)
        assertThat(article.nytdsection).isEqualTo(DummyData.article.nytdsection)
        assertThat(article.adxKeywords).isEqualTo(DummyData.article.adxKeywords)
        assertThat(article.byline).isEqualTo(DummyData.article.byline)
        assertThat(article.type).isEqualTo(DummyData.article.type)
        assertThat(article.title).isEqualTo(DummyData.article.title)
        assertThat(article.abstract).isEqualTo(DummyData.article.abstract)
        assertThat(article.desFacet).isEqualTo(DummyData.article.desFacet)
        assertThat(article.orgFacet).isEqualTo(DummyData.article.orgFacet)
        assertThat(article.perFacet).isEqualTo(DummyData.article.perFacet)
        assertThat(article.media).isEqualTo(DummyData.article.media)
        assertThat(article.etaId).isEqualTo(DummyData.article.etaId)
    }

    @Test
    fun `check that calling fetchRecipes returns error if call fails`() = testDispatcher.runBlockingTest {
        fakeRepository.responseType = ResponseType.ERROR
        val exception: SocketTimeoutException = assertThrows {
            articleUseCase(ArticleByIdUseCase.Params(7, 123456)).collect()
        }
        assertThat(exception).hasMessageThat().isEqualTo(FakeMostPopularArticlesRepository.ERROR_MSG)
    }
}
