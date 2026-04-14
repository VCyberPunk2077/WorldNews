package com.example.vknewsclient.di

import androidx.lifecycle.ViewModel
import com.example.vknewsclient.presentation.main.MainViewModel
import com.example.vknewsclient.presentation.newsfeed.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    @Binds
    fun bindNewsFeedViewModel(impl: NewsFeedViewModel): ViewModel

}