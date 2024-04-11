package com.mlbench.tesla.views.fragments.main

import android.annotation.SuppressLint
import android.os.Bundle
import com.bumptech.glide.Glide
import com.mlbench.tesla.R
import com.mlbench.tesla.base.BaseFragment
import com.mlbench.tesla.base.Inflate
import com.mlbench.tesla.databinding.FragmentDetailBinding
import com.mlbench.tesla.utils.Constants
import com.mlbench.probau.utils.SharePref


class DetailFragment : BaseFragment<FragmentDetailBinding>(){
    lateinit var sharePref: SharePref
    override val inflate: Inflate<FragmentDetailBinding>
        get() = FragmentDetailBinding::inflate

    override fun observeLiveData() {
    }

    override fun init(savedInstanceState: Bundle?) {
        sharePref=SharePref(currentActivity())
        setData()
    }



    @SuppressLint("SetTextI18n")
    private fun setData() {
        val data =sharePref.readArticleData(Constants.ARTICLE_DATA)
        Glide.with(requireActivity()).load(data?.urlToImage).placeholder(R.drawable.article_placeholder).into(binding.image)
        binding.title.text=data?.title
        binding.author.text=data?.author
        binding.url.text=data?.url
        binding.textDate.text=data?.publishedAt
        binding.content.text=data?.content
        binding.source.text=data?.source?.name
        binding.description.text=data?.description




    }


}
