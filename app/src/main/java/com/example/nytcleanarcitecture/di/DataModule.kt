package com.example.nytcleanarcitecture.di

import com.example.nytcleanarcitecture.data.repository.MostPopularArticlesRepositoryImpl
import com.example.nytcleanarcitecture.domain.repository.MostPopularArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface DataModule {

    @get:[Binds Singleton]
    val MostPopularArticlesRepositoryImpl.mostPopularArticlesRepository: MostPopularArticlesRepository
}
