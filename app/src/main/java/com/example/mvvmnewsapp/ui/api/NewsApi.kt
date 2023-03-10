package com.example.mvvmnewsapp.ui.api

import com.example.mvvmnewsapp.ui.model.NewsResponse
import com.example.mvvmnewsapp.ui.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber:Int=1,
        @Query("apiKey")
        apiKey:String=API_KEY


    ):Response<NewsResponse>
    @GET("v2/everything")
    suspend fun serchForNews(
        @Query("Countr")
        countryCode:String="in",
        @Query("page")
        pageNumber:Int=1,
        @Query("apiKey")
        apiKey:String=API_KEY


    ):Response<NewsResponse>
}