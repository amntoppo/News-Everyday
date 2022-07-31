package io.aman.newseveryday.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.aman.newseveryday.model.Article

@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}