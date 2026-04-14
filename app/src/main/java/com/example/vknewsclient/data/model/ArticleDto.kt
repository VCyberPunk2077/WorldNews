package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName


data class ArticleDto(
    @SerializedName("source")
    val source: SourceDto,
    @SerializedName("url")
    val url: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("urlToImage")
    val urlToImage: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
)