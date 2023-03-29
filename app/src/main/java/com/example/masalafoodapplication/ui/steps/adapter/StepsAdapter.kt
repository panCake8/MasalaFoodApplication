package com.example.masalafoodapplication.ui.steps.steps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemStepIngredientBinding

class StepsAdapter(foods: Food) : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {
    private val steps = foods.steps
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): StepsAdapter.StepsViewHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step_ingredient,parent,false)
        return StepsAdapter.StepsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.apply {
            binding.checkBox.text="${position + 1}- ${steps[position]}"
        }
    }
    override fun getItemCount():Int=steps.size


    class StepsViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemStepIngredientBinding.bind(viewItem)
    }
}