package com.example.vknewsclient.presentation.newsfeed

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.domain.usecases.DeletePostUseCase
import com.example.vknewsclient.domain.usecases.GetDataUseCase
import com.example.vknewsclient.domain.usecases.LoadNextDataUseCase
import com.example.vknewsclient.extensions.mergeWith
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(
    application: Application,
    getDataUseCase: GetDataUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : AndroidViewModel(application) {
    private val dataFlow = getDataUseCase()

    private val loadNextDataEvents = MutableSharedFlow<Unit>()
    private val loadNextDataFlow = flow {
        loadNextDataEvents.collect{
            emit(NewsFeedScreenState.Posts(
                posts = dataFlow.value,
                nextDataIsLoading = true
            ))
        }
    }

    val screenState = dataFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(it) as NewsFeedScreenState }
        .onStart { emit(NewsFeedScreenState.Loading) }
        .mergeWith(loadNextDataFlow)


    fun loadNextData() {
        viewModelScope.launch {
            loadNextDataEvents.emit(Unit)
            loadNextDataUseCase()
        }
    }

    fun deletePost(post: FeedPost): Boolean {
        deletePostUseCase(post)
        return true
    }
}