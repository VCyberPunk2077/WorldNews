package com.example.vknewsclient.data.mapper

import com.example.vknewsclient.data.model.ArticleDto
import com.example.vknewsclient.data.model.NewsApiResponseDto
import com.example.vknewsclient.domain.entity.FeedPost
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

class NewsFeedMapper @Inject constructor() {

    fun mapResponseToPosts(response: NewsApiResponseDto): List<FeedPost> {
        val articles = response.articles
        val result = mutableListOf<FeedPost>()
        articles.forEach { dto ->
            val model = mapDtoToModel(dto)
            result.add(model)
        }
        return result
    }

    fun mapDtoToModel(dto: ArticleDto): FeedPost {
        val publicationYear = dto.publishedAt.slice(0..3)
        val dtoMonth = dto.publishedAt.slice(5..6)
        val monthName = Month.of(dtoMonth.toInt())
        val publicationMonth = monthName.getDisplayName(TextStyle.FULL, Locale.getDefault())
        val publicationDay = dto.publishedAt.slice(8..9)
        val publicationTime = dto.publishedAt.slice(11..18)
        val publicationDate = "$publicationDay $publicationMonth $publicationYear $publicationTime"
        return FeedPost(
            id = dto.url,
            sourceName = dto.source.sourceName,
            author = dto.author,
            publicationDate = publicationDate,
            title = dto.title,
            description = dto.description,
            url = dto.url,
            contentImageUrl = dto.urlToImage,
        )
    }
}