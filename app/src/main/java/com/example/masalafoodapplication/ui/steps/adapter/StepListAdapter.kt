package com.example.masalafoodapplication.ui.steps.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemStepIngredientBinding

class StepListAdapter(foods: Food) :
    RecyclerView.Adapter<StepListAdapter.StepViewHolder>() {
    private val steps = foods.steps
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step_ingredient, parent, false)
        return StepViewHolder(view)
    }

    override fun getItemCount(): Int = steps.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.apply {
            binding.checkBox.text = "${position + 1}- ${steps[position]}"
        }
    }

    class StepViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemStepIngredientBinding.bind(viewItem)
    }

}