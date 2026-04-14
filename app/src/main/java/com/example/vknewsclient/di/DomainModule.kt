package com.example.vknewsclient.di

import com.example.vknewsclient.data.repository.NewsFeedRepositoryImpl
import com.example.vknewsclient.domain.repository.NewsFeedRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindNewsFeedRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

}