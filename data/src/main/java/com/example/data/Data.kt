package com.example.data

import android.net.ConnectivityManager
import android.util.Log
import com.example.domain.Base
import com.example.domain.Item
import com.google.gson.GsonBuilder
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class Data {
    var repo: MutableMap <String, MutableList<Base>> = mutableMapOf()

    private fun loadData(
        item: String,
        callback: (List<Base>) -> (Unit)
    ) {
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/karl-park/com.ninetynine.healthysalad/master/server/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        var service: DataGetter = retrofit.create(DataGetter::class.java)
        var call: Call<Item> = service.listItems(item)
        call.enqueue(object : Callback<Item> {
            override fun onFailure(call: Call<Item>, t: Throwable) {
                Log.d("Called", t.toString())
            }
            override fun onResponse(call: Call<Item>, response: Response<Item>) {
                response.body()?.body?.data?.base?.forEach { item -> Log.d("Called", item.name) }
                onLoadSuccess(response, item, callback)
            }
        })
    }

    /*fun loadItem(item: String) {
        val fileName = "$item.json"
        loadData(fileName)
    }*/
    interface NetworkCallback {
        fun onSuccess(item: List<Base>)
    }
    fun loadItem(item: String, callback : (List<Base>) -> (Unit)) {
        val fileName = "$item.json"
        loadData(fileName, callback)
    }
/*
    fun loadAllItems(callback: (List<Base>) -> Unit) {
        var ingredients = listOf<String>("base", "crunchy", "dressing", "protein", "soft")
        for (item in ingredients) {
            loadItem(item,callback)
        }
    }*/
    fun repoWithKey(key: String) : MutableList<Base> = repo[key] ?: mutableListOf()

    fun onLoadSuccess(response: Response<Item>, item: String,callback : (List<Base>) -> (Unit)) {
        var data: MutableList<Base> = response.body()?.body?.data?.base ?: mutableListOf<Base>()
        // Load to repo
        repo[item] = data
        data.forEach { item -> Log.d("Added to repo", item.name) }
        repo.forEach { v -> Log.d("In Repo", "$v" )}
        callback(data)
    }


}