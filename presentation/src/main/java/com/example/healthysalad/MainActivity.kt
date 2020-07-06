package com.example.healthysalad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.data.Data


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Data().loadAllItems()

        var titleFragmentInstance : TitleFragment = TitleFragment()
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main, titleFragmentInstance)
            .addToBackStack(null).commit()


    }
}