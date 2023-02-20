package com.example.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.ui.MainActivity
import com.example.mvvmnewsapp.ui.api.Adapter.NewsAdapter
import com.example.mvvmnewsapp.ui.util.NewsViewModel
import kotlinx.android.synthetic.main.savednewsfragment.*

//import com.example.mvvmnewsapp.ui.util.NewsViewModel

class SavedNewsFragment:Fragment(R.layout.savednewsfragment) {
   lateinit var viewModel: NewsViewModel

    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel=(activity as MainActivity).viewModel
        setupRecyclerView()

        newsAdapter.setOnItemClickListner {
            val bundle=Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }
    }
    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}