package com.example.vknewsclient.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.vknewsclient.R
import com.example.vknewsclient.data.mapper.NewsFeedMapper
import com.example.vknewsclient.data.network.ApiFactory
import com.example.vknewsclient.di.ApplicationScope
import com.example.vknewsclient.domain.entity.AuthState
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.repository.NewsFeedRepository
import com.example.vknewsclient.extensions.isValidApiKey
import com.example.vknewsclient.extensions.mergeWith
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@ApplicationScope
class NewsFeedRepositoryImpl @Inject constructor(
    val mapper: NewsFeedMapper,
    val application: Application
) : NewsFeedRepository {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val sharedPreferences: SharedPreferences = application.getSharedPreferences(
        application.getString(R.string.shared_preferences_key),
        Context.MODE_PRIVATE
    )

    override fun getData(): StateFlow<List<FeedPost>> = data

    private val _authStateFlow = MutableStateFlow<AuthState>(AuthState.Initial)
    override fun getAuthStateFlow(): StateFlow<AuthState> = _authStateFlow

    init {
        val savedKey = sharedPreferences.getString(
            application.getString(R.string.news_api_key),
            ""
        ).orEmpty()

        _authStateFlow.value = if (savedKey.isValidApiKey()) {
            AuthState.Authorized
        } else {
            AuthState.NotAuthorized
        }
    }

    override suspend fun checkAuthState(key: String) {
        sharedPreferences.edit {
            putString(application.getString(R.string.news_api_key), key)
        }

        _authStateFlow.value = if (key.isValidApiKey()) {
            AuthState.Authorized
        } else {
            AuthState.NotAuthorized
        }
    }

    private val nextDataNeededEvents = MutableSharedFlow<Unit>(replay = 1)

    private val refreshedListFlow = MutableSharedFlow<List<FeedPost>>()

    private val loadedListFlow = flow {
        nextDataNeededEvents.emit(Unit)
        nextDataNeededEvents.collect {
            delay(1000)
            val response = apiService.loadPosts(page = page++, key = getApiKey())
            val posts = mapper.mapResponseToPosts(response)
            _feedPosts.addAll(posts)
            emit(feedPosts.distinctBy { it.url })
        }
    }.retry {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }

    private var page: Int = 1
    private val apiService = ApiFactory.apiService

    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private val data: StateFlow<List<FeedPost>> = loadedListFlow
        .mergeWith(refreshedListFlow)
        .stateIn(
            scope = scope,
            SharingStarted.Lazily,
            initialValue = feedPosts
        )

    override suspend fun loadNextData() {
        nextDataNeededEvents.emit(Unit)
    }

    override fun deletePost(feedPost: FeedPost) {
        _feedPosts.remove(feedPost)
        scope.launch {
            refreshedListFlow.emit(feedPosts)
        }
    }

    private fun getApiKey(): String {
        val key = sharedPreferences.getString(application.getString(R.string.news_api_key), "")
            ?: throw RuntimeException("sharedPreferences == null")
        return key
    }

    companion object {

        private const val RETRY_TIMEOUT_MILLIS = 3000L

    }

}