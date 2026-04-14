package com.example.vknewsclient.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.vknewsclient.di.ApplicationComponent
import com.example.vknewsclient.di.DaggerApplicationComponent

class WorldNewsClientApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    Log.d("TEST_TEST", "getApplicationComponent")
    return (LocalContext.current.applicationContext as WorldNewsClientApp).component
}