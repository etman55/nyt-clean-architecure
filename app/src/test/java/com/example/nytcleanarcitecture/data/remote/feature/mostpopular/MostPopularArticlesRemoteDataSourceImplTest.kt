package com.example.nytcleanarcitecture.data.remote.feature.mostpopular

import com.example.nytcleanarcitecture.data.feature.MostPopularArticlesRemoteDataSource
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.model.MostPopularArticlesResponse
import com.example.nytcleanarcitecture.util.ArticlesRequestDispatcher
import com.example.nytcleanarcitecture.util.REQUEST_PATH
import com.example.nytcleanarcitecture.util.makeTestApiService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test

class MostPopularArticlesRemoteDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var remote: MostPopularArticlesRemoteDataSource

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = ArticlesRequestDispatcher()
        mockWebServer.start()
        remote = MostPopularArticlesRemoteDataSourceImpl(makeTestApiService(mockWebServer))
    }

    @Test
    fun `check that calling getMostPopularArticlesByPeriod returns articles list`() = runBlocking {
        val articles: MostPopularArticlesResponse = remote.getMostPopularArticlesByPeriod(7)
        assertThat(articles.results).isNotEmpty()
    }

    @Test
    fun `check that calling getMostPopularArticlesByPeriod returns correct data`() = runBlocking {
        val articles: MostPopularArticlesResponse = remote.getMostPopularArticlesByPeriod(7)
        assertThat(articles.results.first().title).isEqualTo("77 Days: Trump’s Campaign to Subvert the Election")
    }

    @Test
    fun `check that calling getMostPopularArticlesByPeriod makes request to given path`() = runBlocking {
        remote.getMostPopularArticlesByPeriod(7)
        assertThat(REQUEST_PATH).isEqualTo(mockWebServer.takeRequest().path)
    }

    @Test
    fun `check that calling getMostPopularArticlesByPeriod makes a GET request`() = runBlocking {
        remote.getMostPopularArticlesByPeriod(7)
        assertThat("GET $REQUEST_PATH HTTP/1.1").isEqualTo(mockWebServer.takeRequest().requestLine)
    }
}
