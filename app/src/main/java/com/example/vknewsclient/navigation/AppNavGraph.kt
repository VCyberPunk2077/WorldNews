package com.example.vknewsclient.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vknewsclient.domain.entity.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newsFeedScreenContent: @Composable () -> Unit,
    favouriteScreenContent: @Composable () -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
    ) {
        homeScreenNavGraph(newsFeedScreenContent = newsFeedScreenContent)
        composable(
            Screen.Favourite.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
            ) {
            favouriteScreenContent()
        }
    }
}