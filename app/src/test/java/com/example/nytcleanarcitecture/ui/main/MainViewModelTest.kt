package com.example.nytcleanarcitecture.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nytcleanarcitecture.base.Resource
import com.example.nytcleanarcitecture.base.Status
import com.example.nytcleanarcitecture.domain.base.coroutines.CoroutineDispatcherProvider
import com.example.nytcleanarcitecture.domain.data.DummyData
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.fake.FakeMostPopularArticlesRepository
import com.example.nytcleanarcitecture.domain.fake.ResponseType
import com.example.nytcleanarcitecture.domain.feature.ArticleByIdUseCase
import com.example.nytcleanarcitecture.domain.feature.MostPopularArticlesUseCase
import com.example.nytcleanarcitecture.util.MainCoroutineRule
import com.example.nytcleanarcitecture.util.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var testDispatcher: TestCoroutineDispatcher
    private lateinit var coroutinesDispatcherProvider: CoroutineDispatcherProvider
    private lateinit var viewModel: MainViewModel
    private lateinit var articlesUseCase: MostPopularArticlesUseCase
    private lateinit var articleByIdUseCase: ArticleByIdUseCase
    private lateinit var fakeRepository: FakeMostPopularArticlesRepository

    @Before
    fun setUp() {
        testDispatcher = TestCoroutineDispatcher()
        coroutinesDispatcherProvider = CoroutineDispatcherProvider(
            io = testDispatcher,
            computation = testDispatcher,
            ui = testDispatcher
        )
        fakeRepository = FakeMostPopularArticlesRepository()
        articleByIdUseCase = ArticleByIdUseCase(fakeRepository, coroutinesDispatcherProvider)
        articlesUseCase = MostPopularArticlesUseCase(fakeRepository, coroutinesDispatcherProvider)
        viewModel = MainViewModel(articlesUseCase, articleByIdUseCase)
    }

    @Test
    fun `check that getArticles returns status success`() {
        fakeRepository.responseType = ResponseType.DATA
        viewModel.getArticles()
        val articles: Resource<List<Article>> = viewModel.articles.getOrAwaitValueTest()
        assertThat(articles.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `check that getArticles returns status Error`() {
        fakeRepository.responseType = ResponseType.ERROR
        viewModel.getArticles()
        val articles: Resource<List<Article>> = viewModel.articles.getOrAwaitValueTest()
        assertThat(articles.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `check that getArticles returns articles list`() {
        fakeRepository.responseType = ResponseType.DATA
        viewModel.getArticles()
        val articles: List<Article>? = viewModel.articles.getOrAwaitValueTest().data
        assertThat(articles).isNotEmpty()
    }

    @Test
    fun `check that getArticleById returns status success`() {
        fakeRepository.responseType = ResponseType.DATA
        viewModel.getArticleById(id = 123456)
        val articles: Resource<Article> = viewModel.article.getOrAwaitValueTest()
        assertThat(articles.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `check that getArticleById returns status Error`() {
        fakeRepository.responseType = ResponseType.ERROR
        viewModel.getArticleById(id = 123456)
        val articles: Resource<Article> = viewModel.article.getOrAwaitValueTest()
        assertThat(articles.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `check that getArticleById returns article`() {
        fakeRepository.responseType = ResponseType.DATA
        viewModel.getArticleById(id = 123456)
        val article: Article? = viewModel.article.getOrAwaitValueTest().data
        assertThat(article).isEqualTo(DummyData.article)
    }
}
