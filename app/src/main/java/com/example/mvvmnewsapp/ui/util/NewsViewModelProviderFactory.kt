package com.example.mvvmnewsapp.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmnewsapp.ui.Reposiotary.NewsRepositary


class NewsViewModelProviderFactory(
        val newsRepository: NewsRepositary
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(newsRepository) as T
        }
    }
