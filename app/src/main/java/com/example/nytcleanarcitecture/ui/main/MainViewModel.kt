package com.example.nytcleanarcitecture.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.feature.MostPopularArticlesUseCase
import com.example.nytcleanarcitecture.base.BaseViewModel
import com.example.nytcleanarcitecture.base.Resource
import com.example.nytcleanarcitecture.base.extension.mutable
import com.example.nytcleanarcitecture.domain.entity.Article
import com.example.nytcleanarcitecture.domain.feature.ArticleByIdUseCase
import com.example.nytcleanarcitecture.utils.errorMessage
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val articlesUseCase: MostPopularArticlesUseCase,
    private val articleByIdUseCase: ArticleByIdUseCase
) : BaseViewModel<MainNavigation>() {
    private val _articles = mutable<Resource<List<Article>>>()
    val articles: LiveData<Resource<List<Article>>> get() = _articles

    private val _article = mutable<Resource<Article>>()
    val article: LiveData<Resource<Article>> = _article

    fun getArticles(period: Int = 7) {
        viewModelScope.launch {
            articlesUseCase(MostPopularArticlesUseCase.Params(period)).map {
                _articles.postValue(Resource.success(it))
            }.onStart {
                _articles.postValue(Resource.loading())
            }.catch { cause: Throwable ->
                _articles.postValue(Resource.error(msg = cause.errorMessage))
            }.collect()
        }
    }

    fun getArticleById(period: Int = 7, id: Long) {
        viewModelScope.launch {
            articleByIdUseCase(ArticleByIdUseCase.Params(period, id)).map {
                _article.postValue(Resource.success(it))
            }.onStart {
                _article.postValue(Resource.loading())
            }.catch { cause: Throwable ->
                _article.postValue(Resource.error(msg = cause.errorMessage))
            }.collect()
        }
    }

    fun navigateToArticleDetails(articleId: Long) {
        navigationEvent.postValue(MainNavigation.ArticleDetails(articleId))
    }

    fun navigateBack() {
        navigationEvent.postValue(MainNavigation.Back)
    }
}
