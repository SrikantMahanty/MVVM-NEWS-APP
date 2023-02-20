package com.example.mvvmnewsapp.ui.api.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmnewsapp.DetailsActivity
import com.example.mvvmnewsapp.R
import com.example.mvvmnewsapp.ui.fragment.SavedNewsFragment
import com.example.mvvmnewsapp.ui.model.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.ArticleViewHolder> (){
//
    inner class ArticleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    private val differCallback=object :DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem==newItem
        }
    }
    val differ=AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_article_preview,
            parent,
            false
        )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article=differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text=article.source!!.name
            tvTitle.text=article.title
            tvAuthor.text=article.author
            tvDescription.text=article.description
            tvPublishedAt.text=article.publishedAt
//            setOnItemClickListner {
//                onItemClickListener?.let { it (article)}
                holder.itemView.setOnClickListener{
                    Toast.makeText(context,article.title,Toast.LENGTH_SHORT).show()
                    val intent=Intent(context,DetailsActivity::class.java)
                    intent.putExtra("URL",article.url)
                    context.startActivity(intent)
                }


            }
        }



    override fun getItemCount(): Int {
      return  differ.currentList.size
    }
    private var onItemClickListener:((Article)->Unit)?=null
    fun setOnItemClickListner(listener:(Article)->Unit){
        onItemClickListener=listener
    }

}



