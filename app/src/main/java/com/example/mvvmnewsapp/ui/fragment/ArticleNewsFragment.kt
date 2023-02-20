package com.example.mvvmnewsapp.ui.fragment
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.ui.MainActivity
import com.example.mvvmnewsapp.ui.util.NewsViewModel
import kotlinx.android.synthetic.main.articlenewsfragment.*
import java.net.URL

class ArticleNewsFragment:Fragment(R.layout.articlenewsfragment) {
    lateinit var viewModel: NewsViewModel

    val args:ArticleNewsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val article = args.article
        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }

        }
    }
    }
