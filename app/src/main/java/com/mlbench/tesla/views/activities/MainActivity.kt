package com.mlbench.tesla.views.activities

import android.os.Bundle
import com.mlbench.tesla.base.BaseActivity
import com.mlbench.tesla.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun showSnackbar(msg: String?) {
        super.showSnackbar(binding.mLayout, msg)
    }

    override fun attachViewMode() {

    }
}