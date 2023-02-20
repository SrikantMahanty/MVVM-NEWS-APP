package com.example.mvvmnewsapp.ui.util

import android.app.DownloadManager.Query
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnewsapp.ui.Reposiotary.NewsRepositary
import com.example.mvvmnewsapp.ui.model.NewsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepositary: NewsRepositary

) : ViewModel() {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1

var brekingNewsResponse:NewsResponse?=null
    val searchingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchingNewsPage = 1
    var searchingNewsResponse:NewsResponse?=null

    init {
     getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())

        val response =newsRepositary.getBreakingNews(countryCode = countryCode,
            pageNumber = breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }
fun searchnews(searchQuery: String)=viewModelScope.launch {
    searchingNews.postValue(Resource.Loading())
    val response=newsRepositary.getSearchingNews(searchQuery, pageNumber = searchingNewsPage )
    searchingNews.postValue(handleSearchingNewsResponse(response))
}


    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingNewsPage++
                if (brekingNewsResponse==null){
                    brekingNewsResponse=resultResponse
                }else{
                    val oldarticles=brekingNewsResponse?.articles
                    val newsArticles=resultResponse.articles
                    oldarticles?.addAll(newsArticles)
                }
                return Resource.Success(brekingNewsResponse?:resultResponse)
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


    private fun handleSearchingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
//                searchingNewsPage++
//                if (searchingNewsResponse==null){
//                    searchingNewsResponse=resultResponse
//                }else{
//                    val oldarticles=searchingNewsResponse?.articles
//                    val newsArticles=resultResponse.articles
//                    oldarticles?.addAll(newsArticles)
//                }
                //return Resource.Success(searchingNewsResponse?:resultResponse)
                return Resource.Success(resultResponse)
            }
        }
        //return Resource.Error(response.message())
        return Resource.Error(response.message())
    }
}