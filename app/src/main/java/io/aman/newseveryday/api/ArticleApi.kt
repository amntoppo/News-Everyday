package io.aman.newseveryday.api

import io.aman.newseveryday.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "0956291c11ae47c6be51836bdf61e0d2"
    }

    @GET("top-headlines?country=in&apiKey=$API_KEY")
    suspend fun getTopHeadines(): NewsModel

    @GET("everything")
    suspend fun searchForNews(@Query("q") searchString: String, @Query("from") date: String, @Query("sortBy") sortBy: String, @Query("apiKey") KEY: String) : NewsModel

    @GET("everything")
    suspend fun getCategoryNews(@Query("q") category: String, @Query("from") date: String, @Query("sortBy") sortBy: String, @Query("apiKey") KEY: String): NewsModel
}