package io.aman.newseveryday.ui.search

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.aman.newseveryday.api.ArticleApi
import io.aman.newseveryday.api.ArticleApi.Companion.API_KEY
import io.aman.newseveryday.data.repository.ArticleRepository
import io.aman.newseveryday.model.Article
import io.aman.newseveryday.model.NewsModel
import io.aman.newseveryday.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ArticleRepository
) : ViewModel() {

    private val newsLiveData = MutableLiveData<NewsModel>()
    val news: LiveData<NewsModel> = newsLiveData

    fun getSearchDataRepository(category: String): LiveData<Resource<List<Article>>> {
        return repository.getCategoryArticle(category).asLiveData()
    }
}