package com.example.vknewsclient.data.network

import com.example.vknewsclient.data.model.NewsApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything?q=-bitcoin&sortBy=publishedAt&language=ru&pageSize=50")
    suspend fun loadPosts(
        @Query("page") page: Int,
        @Query("apiKey") key: String
    ): NewsApiResponseDto

}