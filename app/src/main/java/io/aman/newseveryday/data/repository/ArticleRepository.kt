package io.aman.newseveryday.data.repository

import androidx.room.withTransaction
import io.aman.newseveryday.api.ArticleApi
import io.aman.newseveryday.data.ArticleDatabase
import io.aman.newseveryday.utils.NetworkBoundResource
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val api: ArticleApi,
    private val db: ArticleDatabase
) {
    private val articleDao = db.articleDao()

    fun getArticle() = NetworkBoundResource(
        query = {
            articleDao.getAllArticles()
        },
        fetch = {
            api.getTopHeadines().articles
        },
        saveFetchRequest = { articles ->
            db.withTransaction {
                articleDao.delectAllArticles()

                articleDao.insertArticles(articles)
            }
        }
    )

    fun getCategoryArticle(category: String) = NetworkBoundResource(
        query = {
                articleDao.searchArticle(category)
        },
        fetch = {
            api.getCategoryNews(category, "2022-07-30", "popularity", ArticleApi.API_KEY).articles
        },
        saveFetchRequest = { articles ->
            db.withTransaction {
                articleDao.insertArticles(articles)
            }        }
    )

    fun getSearchArticle(searchString: String) = NetworkBoundResource(
        query = {
            articleDao.searchArticle(searchString)
        },
        fetch = {
            api.searchForNews(searchString, "2022-07-30", "popularity", ArticleApi.API_KEY).articles
        },
        saveFetchRequest = { articles ->
            db.withTransaction {
                articleDao.insertArticles(articles)
            }
        }
    )


}