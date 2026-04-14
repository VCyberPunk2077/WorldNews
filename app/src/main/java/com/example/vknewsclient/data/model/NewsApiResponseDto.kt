package com.example.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class NewsApiResponseDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>
)