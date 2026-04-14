package com.example.vknewsclient.di

import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.data.network.ApiService
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    companion object {
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }

}