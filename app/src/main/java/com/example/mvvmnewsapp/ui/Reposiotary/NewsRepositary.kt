package com.example.mvvmnewsapp.ui.Reposiotary

import com.example.mvvmnewsapp.ui.api.RetrofitInstance
import com.example.mvvmnewsapp.ui.db.ArtcleDatabase

class NewsRepositary (
    val db:ArtcleDatabase

){
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchingNews(searchQuery: String,pageNumber: Int)=
        RetrofitInstance.api.getBreakingNews(searchQuery, pageNumber)
}