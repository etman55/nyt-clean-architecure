package com.example.nytcleanarcitecture.di

import com.example.nytcleanarcitecture.utils.ImageLoader
import com.example.nytcleanarcitecture.utils.ImageLoaderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)
@Module
interface ImageLoaderModule {

    @get:Binds
    val ImageLoaderImpl.imageLoader: ImageLoader
}
