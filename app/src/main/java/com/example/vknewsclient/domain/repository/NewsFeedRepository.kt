package com.example.vknewsclient.domain.repository

import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.entity.AuthState
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getData(): StateFlow<List<FeedPost>>

    suspend fun checkAuthState(key: String)

    suspend fun loadNextData()

    fun deletePost(feedPost: FeedPost)

}