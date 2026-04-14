package com.example.vknewsclient.navigation

sealed class Screen(
    val route: String
) {

    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Favourite: Screen(ROUTE_FAVOURITE)
    object Home: Screen(ROUTE_HOME)

    companion object {
        private const val ROUTE_HOME = "home"
        private const val ROUTE_NEWS_FEED = "news_feed"
        private const val ROUTE_FAVOURITE = "favourite"

    }
}