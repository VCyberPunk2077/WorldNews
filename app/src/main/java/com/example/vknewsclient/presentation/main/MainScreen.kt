package com.example.vknewsclient.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vknewsclient.R
import com.example.vknewsclient.navigation.AppNavGraph
import com.example.vknewsclient.navigation.rememberNavigationState
import com.example.vknewsclient.presentation.ViewModelFactory
import com.example.vknewsclient.presentation.newsfeed.NewsFeedScreen
import com.example.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    VkNewsClientTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.surface,
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                ) {
                    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                    val items = listOf(
                        NavigationItem.Home, NavigationItem.Favourite
                    )
                    items.forEach { item ->
                        val selected = navBackStackEntry?.destination?.hierarchy?.any {
                            it.route == item.screen.route
                        } ?: false
                        NavigationBarItem(
                            selected = selected, onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        }, icon = {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = stringResource(item.titleResId)
                                )
                                Text(text = stringResource(item.titleResId))

                            }
                        }, colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            selectedIconColor = MaterialTheme.colorScheme.onSurface
                        )
                        )
                    }
                }
            },
        ) { paddingValues ->
            AppNavGraph(
                navHostController = navigationState.navHostController,
                newsFeedScreenContent = {
                    NewsFeedScreen(paddingValues = paddingValues)
                },
                favouriteScreenContent = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.favourite_screen_is_in_progress),
                            textAlign = TextAlign.Center
                        )
                    }
                })
        }

    }
}