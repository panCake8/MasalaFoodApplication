package com.example.masalafoodapplication.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.Food
import com.example.masalafoodapplication.databinding.ItemStepIngredientBinding

class StepsAdapter(foods: Food):RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {
    val  steps = foods.makeRecipe.split(";").toTypedArray()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val view=  LayoutInflater.from(parent.context).inflate(R.layout.item_step_ingredient,parent,false)
        return  StepsViewHolder(view)
    }

    override fun getItemCount(): Int = steps.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.apply {
            binding.checkBox.text=  "${position+1}- ${steps[position]}"
        }
    }
    class StepsViewHolder(viewItem: View)  :RecyclerView.ViewHolder(viewItem){
        val binding= ItemStepIngredientBinding.bind(viewItem)
    }
}