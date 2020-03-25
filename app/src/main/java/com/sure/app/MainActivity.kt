package com.sure.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sure.studynote.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_cpp.text = TestSoHelper().intFromJNI(1, 2).toString()

    }
}
