package com.example.appleofmyeye

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appleofmyeye.api.Hit
import com.example.appleofmyeye.api.Recipe

class RecipeAdapter(val recipes: List<Hit>): RecyclerView.Adapter<RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        return holder.bind(recipes[position])
    }
}

class RecipeViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    private val photo:ImageView = itemView.findViewById(R.id.recipe_photo)
    fun bind(hit: Hit) {
        println(hit)
        Glide.with(itemView.context).load(hit.recipe.image).into(photo)
        photo.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(hit.recipe.url)
            itemView.context.startActivity(i)
        }
    }

}