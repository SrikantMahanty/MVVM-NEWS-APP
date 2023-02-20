package com.example.mvvmnewsapp.ui.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvmnewsapp.ui.model.Article

@Database(
 entities = [Article::class],
 version = 1
)
@TypeConverters(Converters::class)
 abstract  class ArtcleDatabase:RoomDatabase() {

    abstract fun  getArticleDao():ArticleDao

    companion object{
     private var instance:ArtcleDatabase?=null
     private val Lock=Any()


     operator fun invoke(context: Context)= instance?: synchronized(Lock){
      instance?: createDatabase(context).also{
       instance=it}

      }
     private  fun createDatabase(context: Context)=
      Room.databaseBuilder(
       context.applicationContext,
       ArtcleDatabase::class.java,
       "article_db"
      ).build()
     }
    }
