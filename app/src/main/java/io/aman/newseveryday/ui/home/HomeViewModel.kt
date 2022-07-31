package io.aman.newseveryday.ui.home

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.aman.newseveryday.data.repository.ArticleRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: ArticleRepository
): ViewModel() {
    val articles = repository.getArticle().asLiveData()
}