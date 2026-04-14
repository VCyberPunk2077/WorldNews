package com.example.vknewsclient.di

import android.app.Application
import com.example.vknewsclient.presentation.ViewModelFactory
import com.example.vknewsclient.presentation.WorldNewsClientApp
import com.example.vknewsclient.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DomainModule::class,
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory


    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent

    }
}