package com.example.nytcleanarcitecture.di

import android.content.Context
import com.example.nytcleanarcitecture.BuildConfig
import com.example.nytcleanarcitecture.data.base.RetrofitFactory
import com.example.nytcleanarcitecture.data.remote.feature.mostpopular.service.ApiHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @[Provides Singleton]
    fun provideBaseURL(): String = BuildConfig.BASE_URL

    @[Provides Singleton]
    fun provideApiHandler(
        baseUrl: String,
        @ApplicationContext context: Context
    ): ApiHandler {
        return RetrofitFactory.makeServiceHandler(
            baseUrl,
            ApiHandler::class.java,
            BuildConfig.DEBUG,
            context
        ) as ApiHandler
    }
}
