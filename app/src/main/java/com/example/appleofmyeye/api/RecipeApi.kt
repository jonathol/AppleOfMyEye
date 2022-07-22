package com.example.appleofmyeye.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {

    @GET("search")
    fun getRecipes(@Query("q") food: String, @Query("app_id") id: String, @Query("app_key") key: String): Call<RecipeList>
}