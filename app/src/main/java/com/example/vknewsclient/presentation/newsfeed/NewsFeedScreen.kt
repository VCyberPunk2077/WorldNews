package com.example.vknewsclient.presentation.newsfeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vknewsclient.domain.entity.FeedPost
import com.example.vknewsclient.presentation.getApplicationComponent

@Composable
fun NewsFeedScreen(
    paddingValues: PaddingValues,
) {
    val component = getApplicationComponent()
    val viewModel: NewsFeedViewModel = viewModel(
        factory = component.getViewModelFactory()
    )
    val screenState = viewModel.screenState.collectAsState(NewsFeedScreenState.Initial)
    NewsFeedScreenContent(
        screenState = screenState,
        paddingValues = paddingValues,
        viewModel = viewModel
    )
}

@Composable
fun NewsFeedScreenContent(
    screenState: State<NewsFeedScreenState>,
    paddingValues: PaddingValues,
    viewModel: NewsFeedViewModel
) {
    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Initial -> {

        }

        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                feedPosts = currentState.posts,
                viewModel = viewModel,
                paddingValues = paddingValues,
                nextDataIsLoading = currentState.nextDataIsLoading
            )
        }

        NewsFeedScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun FeedPosts(
    feedPosts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    nextDataIsLoading: Boolean
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 8.dp,
            end = 8.dp,
            top = paddingValues.calculateTopPadding(),
            bottom = paddingValues.calculateBottomPadding()
        ), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(feedPosts, key = { it.id }) { post ->

            val dismissState = rememberSwipeToDismissBoxState(
                positionalThreshold = { it * 0.3F })

            SwipeToDismissBox(
                modifier = Modifier.animateItem(),
                state = dismissState,
                enableDismissFromStartToEnd = false,
                enableDismissFromEndToStart = true,
                onDismiss = { value ->
                    val isDismissed = value in setOf(
                        SwipeToDismissBoxValue.EndToStart
                    )
                    if (isDismissed) {
                        viewModel.deletePost(post)
                    }
                },
                backgroundContent = {
                    Box(
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxSize()
                    )
                }) {
                if (post.title != null) {
                    PostCard(feedPost = post)
                }
            }
        }
        item {
            if (nextDataIsLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            } else {
                SideEffect {
                    viewModel.loadNextData()
                }
            }
        }
    }
}