package io.aman.newseveryday.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.aman.newseveryday.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Insert(onConflict = REPLACE)
    suspend fun insertArticles(article: List<Article>)

    @Query("DELETE FROM articles")
    suspend fun delectAllArticles()

    @Query("SELECT * FROM articles WHERE title LIKE '%' || :category || '%'")
    fun searchArticle(category: String): Flow<List<Article>>
}