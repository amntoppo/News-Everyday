package io.aman.newseveryday.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.aman.newseveryday.api.ArticleApi
import io.aman.newseveryday.api.ArticleApi.Companion.BASE_URL
import io.aman.newseveryday.data.ArticleDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideArticleApi(retrofit: Retrofit) : ArticleApi =
        retrofit.create(ArticleApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : ArticleDatabase =
        Room.databaseBuilder(app, ArticleDatabase::class.java,"article_database")
            .build()
}