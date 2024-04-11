package com.mlbench.tesla.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.mlbench.tesla.models.WeatherUpdatesModel

object Helper {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            networkCapabilities != null && (
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    )
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

    fun showSoftKeyboard(context: Context, et: EditText) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT)
    }

    fun hideSoftKeyboard(context: Context, et: EditText) {
        et.clearFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et.windowToken, 0)
    }

    fun filterArticles(articles: List<WeatherUpdatesModel.Article>, query: String): List<Int> {
        return articles.mapIndexedNotNull { index, article ->
            if ((article.author?.contains(query, ignoreCase = true) == true) ||
                (article.content?.contains(query, ignoreCase = true) == true) ||
                (article.description?.contains(query, ignoreCase = true) == true) ||
                (article.publishedAt?.contains(query, ignoreCase = true) == true) ||
                (article.source.name?.contains(query, ignoreCase = true) == true) ||
                (article.title?.contains(query, ignoreCase = true) == true) ||
                (article.url?.contains(query, ignoreCase = true) == true) ||
                (article.urlToImage?.contains(query, ignoreCase = true) == true)
            ) {
                index
            } else {
                null
            }
        }
    }
}