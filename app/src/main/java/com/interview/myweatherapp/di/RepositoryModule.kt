package com.Olehbil_channel.myweatherapp.di

import com.Olehbil_channel.myweatherapp.repository.Repository
import com.Olehbil_channel.myweatherapp.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun getRepository(repository: RepositoryImpl): Repository
}