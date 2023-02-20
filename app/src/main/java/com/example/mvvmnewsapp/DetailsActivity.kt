package com.example.mvvmnewsapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val url=intent.getStringExtra("URL")
        if(url!=null){
            detailwebView.settings.javaScriptEnabled=true
            detailwebView.webViewClient=object:WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    detailProgressBar.visibility= View.GONE
                    detailwebView.visibility=View.VISIBLE
                }
            }
            detailwebView.loadUrl(url)

        }
    }
}