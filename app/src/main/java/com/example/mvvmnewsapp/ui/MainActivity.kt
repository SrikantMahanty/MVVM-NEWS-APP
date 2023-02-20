package com.example.mvvmnewsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.ui.Reposiotary.NewsRepositary
import com.example.mvvmnewsapp.ui.db.ArtcleDatabase
import com.example.mvvmnewsapp.ui.util.NewsViewModel
import com.example.mvvmnewsapp.ui.util.NewsViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val newsRepositary= NewsRepositary(ArtcleDatabase(this))
        val viewModelProviderFactory= NewsViewModelProviderFactory(newsRepositary )
        viewModel= ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)
        val navHostFragment= supportFragmentManager.findFragmentById(R.id.navhostfragment)
                as NavHostFragment
        val navController= navHostFragment.navController
        btmNavigationview.setupWithNavController(navController)
         //   btmNavigationview.setupWithNavController(navhostfragment.findNavController())
    }
}