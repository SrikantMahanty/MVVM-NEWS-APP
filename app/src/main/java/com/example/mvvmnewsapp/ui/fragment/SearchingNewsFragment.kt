package com.example.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.ui.MainActivity
import com.example.mvvmnewsapp.ui.api.Adapter.NewsAdapter
import com.example.mvvmnewsapp.ui.util.NewsViewModel
import com.example.mvvmnewsapp.ui.util.Resource
import kotlinx.android.synthetic.main.breakingnewsfragment.*
import kotlinx.android.synthetic.main.searchnewsfragment.*
import kotlinx.android.synthetic.main.searchnewsfragment.paginationProgressBar
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//import com.example.mvvmnewsapp.ui.util.NewsViewModel

class SearchingNewsFragment : Fragment(R.layout.searchnewsfragment) {
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    val TAG = "SearchNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        newsAdapter.setOnItemClickListner {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }
        var job: Job? = null
        etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(1000L)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchnews(editable.toString())
                    }
                }
            }

        }
        viewModel.searchingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                     //   val totalpages=newsResponse.totalResults/20+2
//                        isLastpage=viewModel.searchingNewsPage==totalpages
//                        if (isLastpage){
//                            rvBreakingNews.setPadding(0,0,0,0)
//                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading<*> -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        //isLoading=false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
       // isLoading=true
    }
//
//    var isLoading = false
//    var isLastpage = false
//    var isscroling = false

    val scrollListener=object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                //isscroling=true


            }
        }
//
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//            val layoutManager=recyclerView.layoutManager as LinearLayoutManager
//            val firstVisibleItemPosition=layoutManager.findFirstVisibleItemPosition()
//            val visibleItemCount=layoutManager.childCount
//            val totalItemCount=layoutManager.itemCount
//
//            val isNotLoadingAndNotLastPage=!isLoading &&!isLastpage
//            val isAtLastitem=firstVisibleItemPosition+visibleItemCount>=totalItemCount
//            val isNotBegnining =firstVisibleItemPosition>=0
//            val isTotalMorethanvisible=totalItemCount>=20
//            val shouldpaginate=isNotLoadingAndNotLastPage &&isAtLastitem && isNotBegnining
//                    &&isTotalMorethanvisible && isscroling
//            if(shouldpaginate){
//                viewModel.searchnews(etSearch.text.toString())
//                isscroling=false
//            }
//        }

    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
          //  addOnScrollListener(this@SearchingNewsFragment.scrollListener)
        }
    }
}