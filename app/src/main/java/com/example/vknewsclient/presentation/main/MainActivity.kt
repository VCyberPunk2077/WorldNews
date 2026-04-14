package com.example.vknewsclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.entity.AuthState
import com.example.vknewsclient.presentation.getApplicationComponent
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val component = getApplicationComponent()
            val viewModel: MainViewModel = viewModel(
                factory = component.getViewModelFactory()
            )
            val authState = viewModel.authState.collectAsState(AuthState.Initial)
            VkNewsClientTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                ) { innerPadding ->
                    innerPadding.toString()
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.surface)
                            .windowInsetsPadding(WindowInsets.systemBars)
                    ) {
                        when (authState.value) {
                            AuthState.Authorized -> {
                                MainScreen()
                            }
                            AuthState.Initial -> {}
                            AuthState.NotAuthorized -> {
                                LoginScreen(
                                    onPrivateKeyLoginClicked = { key ->
                                        viewModel.registerAuthKey(key)
                                    },
                                    onSharedKeyLoginClicked = {
                                        viewModel.registerAuthKey()
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
