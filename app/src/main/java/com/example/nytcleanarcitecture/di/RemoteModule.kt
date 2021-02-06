package com.example.nytcleanarcitecture.di

import com.example.nytcleanarcitecture.data.feature.MostPopularArticlesRemoteDataSource
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.MostPopularArticlesRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface RemoteModule {

    @get:[Binds Singleton]
    val MostPopularArticlesRemoteDataSourceImpl.mostPopularArticlesRemoteDataSource: MostPopularArticlesRemoteDataSource
}
