package com.example.vknewsclient.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.R
import com.example.vknewsclient.domain.usecases.CheckAuthStatusUseCase
import com.example.vknewsclient.domain.usecases.GetAuthStateFlowUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    getAuthStateFlowUseCase: GetAuthStateFlowUseCase,
    private val checkAuthStatusUseCase: CheckAuthStatusUseCase
) : AndroidViewModel(application) {

    val authState = getAuthStateFlowUseCase()

    fun registerAuthKey(key: String = application.getString(R.string.developer_api_key)) {
        viewModelScope.launch {
            Log.d("TEST_TEST", "registerAuthKey: $key")
            checkAuthStatusUseCase.invoke(key)
        }
    }

}

