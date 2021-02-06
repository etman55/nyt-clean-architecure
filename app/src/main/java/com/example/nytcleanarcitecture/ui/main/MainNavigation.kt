package com.example.nytcleanarcitecture.ui.main

import com.example.nytcleanarcitecture.base.Navigation

sealed class MainNavigation : Navigation {
    object Back : MainNavigation()
    data class ArticleDetails(val articleId: Long) : MainNavigation()
}
