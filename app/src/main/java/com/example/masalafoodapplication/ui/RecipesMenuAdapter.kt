package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.ItemRecipesBinding
import com.example.masalafoodapplication.util.loadImage

import com.kiko.fillapp.data.domain.Food

class RecipesMenuAdapter (val list: List<Food>): RecyclerView.Adapter<RecipesMenuAdapter.RecipesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_recipes,parent,false)
        return  RecipesViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipe =list[position]
        holder.binding.apply {

            recipeName1.text = currentRecipe.recipeName
            prepareTime1.text = currentRecipe.timeMinutes.toString() +"m"
            imageRecipe1.loadImage(currentRecipe.imageUrl)
        }
    }
    class RecipesViewHolder(viewItem: View) :RecyclerView.ViewHolder(viewItem) {
        val binding = ItemRecipesBinding.bind(viewItem)

    }
}