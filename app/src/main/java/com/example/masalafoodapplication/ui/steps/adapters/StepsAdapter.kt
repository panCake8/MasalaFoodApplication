package com.example.masalafoodapplication.ui.steps.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemStepIngredientBinding

class StepsAdapter(foods: Food) : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {
    private val steps = foods.steps
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step_ingredient, parent, false)
        return StepsViewHolder(view)
    }

    override fun getItemCount(): Int = steps.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.apply {
            binding.checkBox.text = "${position + 1}- ${steps[position]}"
        }
    }

    class StepsViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemStepIngredientBinding.bind(viewItem)
    }
}