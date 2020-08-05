package com.example.healthysalad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var titleFragment : TitleFragment = TitleFragment.newInstance()

        this.supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main, titleFragment)
            .addToBackStack(null).commit()
    }
}