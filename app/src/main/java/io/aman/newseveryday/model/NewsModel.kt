package io.aman.newseveryday.model

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)