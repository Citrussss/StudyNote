package com.sure.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sure.home.R

class HomeActivity : AppCompatActivity() {

    val viewModel: HomeViewModel by lazy {
        return@lazy ViewModelProvider(this, HomeViewModelFactory()).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
