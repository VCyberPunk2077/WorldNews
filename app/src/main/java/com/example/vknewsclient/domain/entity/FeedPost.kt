package com.example.vknewsclient.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class FeedPost(
    val id: String,
    val sourceName: String,
    val author: String?,
    val publicationDate: String,
    val title: String?,
    val description: String,
    val url: String,
    val contentImageUrl: String?
)