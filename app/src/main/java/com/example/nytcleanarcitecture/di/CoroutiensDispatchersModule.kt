package com.example.nytcleanarcitecture.di

import com.example.domain.base.coroutines.CoroutineDispatcherProvider
import com.example.domain.base.coroutines.CoroutineDispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
interface CoroutiensDispatchersModule {
    @get:[Binds Singleton]
    val CoroutineDispatcherProviderImpl.coroutineDispatcherProvider: CoroutineDispatcherProvider
}
