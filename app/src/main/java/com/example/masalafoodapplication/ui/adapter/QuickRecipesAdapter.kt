package com.example.masalafoodapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.ItemFoodBinding
import com.example.masalafoodapplication.util.loadImage
import com.kiko.fillapp.data.domain.Food

class QuickRecipesAdapter(val list:List<Food>):RecyclerView.Adapter<QuickRecipesAdapter.RecipesViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food,parent,false)
        return RecipesViewHolder(view)

    }

    override fun getItemCount() =list.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipe = list[position]
        holder.binding.apply {
            recipeName.text =currentRecipe.recipeName
            prepareTime.text = currentRecipe.timeMinutes.toString()
            imageRecipe.loadImage(currentRecipe.imageUrl)
        }

    }
    class RecipesViewHolder(viewItem : View):RecyclerView.ViewHolder(viewItem){
        val binding  = ItemFoodBinding.bind(viewItem)
    }

}