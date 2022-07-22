package com.example.appleofmyeye.api

data class RecipeList(
    val hits: List<Hit>
)

data class Hit(
    val recipe: Recipe
)

data class Recipe(
    val image: String,
    val url: String,
)