package com.sure.home.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sure.home.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    val viewModel: HomeViewModel by lazy {
        return@lazy ViewModelProvider(this).get(HomeViewModel::class.java)
//        return@lazy ViewModelProvider.NewInstanceFactory().create(HomeViewModel::class.java)
//        return@lazy ViewModelProvider(this, HomeViewModelFactory()).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel.content.observe(this, Observer {
            tv_content.text = it
        })
        viewModel.loadData()
    }
}
