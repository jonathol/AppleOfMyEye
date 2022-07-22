package com.example.appleofmyeye

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appleofmyeye.api.RecipeApi
import com.example.appleofmyeye.api.RecipeList
import com.example.appleofmyeye.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val imageBitmap = intent.getBundleExtra("imagedata")["data"] as Bitmap?
        val pred = intent.getStringExtra("pred")
        val imageView = findViewById<ImageView>(R.id.image)
        imageView.setImageBitmap(imageBitmap)
        val textView = findViewById<TextView>(R.id.label)
        textView.text = pred

        val request = ServiceBuilder.buildService(RecipeApi::class.java)
        val call = request.getRecipes(pred +" apple", getString(R.string.recipe_id), getString(R.string.recipe_api_key))

        call.enqueue(object : Callback<RecipeList>{
            override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                if (response.isSuccessful){
                    var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        println(response.body())
                        adapter = RecipeAdapter(response.body()!!.hits)
                    }
                    recyclerView.suppressLayout(true)

                }
            }

            override fun onFailure(call: Call<RecipeList>, t: Throwable) {
            }
        })
    }
}