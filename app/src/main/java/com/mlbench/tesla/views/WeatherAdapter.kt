package com.mlbench.tesla.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlbench.tesla.databinding.CustomLayoutBinding
import com.mlbench.tesla.models.WeatherUpdatesModel
import com.mlbench.tesla.utils.Helper

class WeatherAdapter (
    var context: Context,
    var mList: List<WeatherUpdatesModel.Article>,
    var onclick:(WeatherUpdatesModel.Article)->Unit,

) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private var filteredArticles: List<WeatherUpdatesModel.Article> = mList
    inner class ViewHolder(private val binding: CustomLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(model: WeatherUpdatesModel.Article) {
            binding.title.text = model.title
            binding.textDate.text=model.publishedAt
            binding.description.text=model.description.replace("\\s","")
            binding.root.setOnClickListener {
                onclick(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomLayoutBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredArticles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(filteredArticles[position])
    }
    fun filter(query: String) {
        val indices = Helper.filterArticles(mList, query)
        filteredArticles = indices.map { mList[it] }
        notifyDataSetChanged()
    }


}