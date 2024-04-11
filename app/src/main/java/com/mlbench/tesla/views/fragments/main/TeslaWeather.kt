package com.mlbench.tesla.views.fragments.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mlbench.tesla.R
import com.mlbench.tesla.views.WeatherAdapter
import com.mlbench.tesla.base.BaseFragment
import com.mlbench.tesla.base.Inflate
import com.mlbench.tesla.databinding.FragmentWeatherBinding
import com.mlbench.tesla.models.WeatherUpdatesModel
import com.mlbench.tesla.repository.NetworkResult
import com.mlbench.tesla.utils.Helper
import com.mlbench.tesla.viewModel.WeatherVM
import com.mlbench.tesla.views.activities.MainActivity
import com.mlbench.probau.utils.SharePref
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeslaWeather : BaseFragment<FragmentWeatherBinding>(){
    val weatherVM by viewModels<WeatherVM>()
    lateinit var data: List<WeatherUpdatesModel.Article>
    lateinit var adapterweather: WeatherAdapter
    lateinit var sharePref: SharePref

    override val inflate: Inflate<FragmentWeatherBinding>
        get() = FragmentWeatherBinding::inflate

    override fun observeLiveData() {
        weatherVM.weatherResponseLiveData.observe(viewLifecycleOwner) {
            hideLoadingBar()
            when (it) {
                is NetworkResult.Success -> {
                    it.data?.let { response ->
                        response.articles.let { data ->
                            this.data=data
                            showResults(data)
                        }
                    }
                    weatherVM.weatherResponseLiveData.value = null
                }

                is NetworkResult.Error -> {
                    (currentActivity() as MainActivity).showSnackbar(it.message)
                    weatherVM.weatherResponseLiveData.value = null
                }

                is NetworkResult.Loading -> {
                    showLoadingBar()
                }
            }
        }

    }
    override fun init(savedInstanceState: Bundle?) {
        sharePref=SharePref(currentActivity())
        weatherVM.weatherlist(requireContext())
        searchMethods()
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun showResults(data: List<WeatherUpdatesModel.Article>) {
        binding.recyclerview.apply {
            adapterweather= WeatherAdapter(requireContext(),data) { it ->
                sharePref.writeArticleData(it)
                currentActivity().replaceFragmentInAuth(R.id.socializefragment,)
            }
            adapter=adapterweather
            layoutManager = LinearLayoutManager(currentActivity())
            adapterweather.notifyDataSetChanged()
        }
    }


    private fun searchMethods() {

        binding.etSearch.setOnEditorActionListener(
            TextView.OnEditorActionListener { v, actionId, event ->

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (binding.etSearch.text.toString().isNotEmpty()) {
                        Helper.hideSoftKeyboard(currentActivity(), binding.etSearch)
                        return@OnEditorActionListener true
                    }
                }
                false
            })

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.etSearch.text.toString().isNotEmpty()) {
                    adapterweather.filter(binding.etSearch.text.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                if (query.isNotEmpty()){
                    adapterweather.filter(query)
                }

            }

        })

    }



}