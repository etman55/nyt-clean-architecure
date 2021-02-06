package com.example.nytcleanarcitecture.di

import com.example.nytcleanarcitecture.domain.base.coroutines.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers

@InstallIn(ApplicationComponent::class)
@Module
class CoroutiensDispatchersModule {

    @[Provides Singleton]
    fun provideCoroutiensDispatcher() = CoroutineDispatcherProvider(
        Dispatchers.Main,
        Dispatchers.Default,
        Dispatchers.IO
    )
}
