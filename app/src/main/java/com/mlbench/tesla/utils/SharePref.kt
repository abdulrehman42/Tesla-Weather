package com.mlbench.probau.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mlbench.tesla.models.WeatherUpdatesModel
import com.mlbench.tesla.utils.Constants
import com.mlbench.tesla.utils.Constants.PREFS_TOKEN_FILE
import dagger.hilt.android.qualifiers.ApplicationContext
import java.lang.reflect.Type
import javax.inject.Inject

class SharePref @Inject constructor(@ApplicationContext ctx: Context) {
    val gson = Gson()
    var prefs: SharedPreferences? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        @SuppressLint("StaticFieldLeak")
        var mInstence: SharePref? = null

        fun init(context: Context?) {
            mContext = context
        }

        fun getInstance(): SharePref? {
            if (mInstence != null) {
                return mInstence
            } else {
                mInstence = SharePref(mContext!!.applicationContext)
            }
            return mInstence
        }
    }

    var isThisSessionFromLink = false

    init {
        prefs = ctx.getSharedPreferences(PREFS_TOKEN_FILE, Context.MODE_PRIVATE)
        isThisSessionFromLink = false
    }

    fun writeArticleData(user:WeatherUpdatesModel.Article) {
        val data = gson.toJson(user)
        prefs!!.edit().putString(Constants.ARTICLE_DATA, data).apply()
    }
    fun readArticleData(key: String?): WeatherUpdatesModel.Article? {
        val gson = Gson()
        val jsonOutput = prefs?.getString(key, null)
        val articleData: Type =
            object : TypeToken<WeatherUpdatesModel.Article?>() {}.type
        return gson.fromJson(jsonOutput, articleData)
    }





}