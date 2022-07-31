package io.aman.newseveryday.ui.categoryNews

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.aman.newseveryday.api.ArticleApi
import io.aman.newseveryday.data.repository.ArticleRepository
import io.aman.newseveryday.model.Article
import io.aman.newseveryday.utils.Resource
import javax.inject.Inject

@HiltViewModel
class CategoryNewsViewModel @Inject constructor(
    private val api: ArticleApi,
    private val repository: ArticleRepository
) : ViewModel() {

    fun getCategoryDataRepository(category: String): LiveData<Resource<List<Article>>> {
        return repository.getCategoryArticle(category).asLiveData()
    }
}
