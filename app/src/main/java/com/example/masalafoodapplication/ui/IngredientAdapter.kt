package com.example.masalafoodapplication.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.Food
import com.example.masalafoodapplication.databinding.ItemStepIngredientBinding



class IngredientAdapter(foods: Food) :
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    val ingeredient = foods?.ingredients?.split(";")?.toTypedArray()
    val  ingeredient = foods.ingredients.split(";").toTypedArray()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step_ingredient, parent, false)
        return IngredientViewHolder(view)
    }

    override fun getItemCount(): Int = ingeredient.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.apply {
            binding.checkBox.text=  "${position+1}- ${ingeredient[position]}"
            binding.checkBox.text = "${position + 1}- ${ingeredient!![position]}"
        }
    }

    class IngredientViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemStepIngredientBinding.bind(viewItem)
    }
}