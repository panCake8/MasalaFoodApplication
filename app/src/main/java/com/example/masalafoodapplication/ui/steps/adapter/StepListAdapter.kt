package com.example.masalafoodapplication.ui.steps.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.ItemStepCheckboxBinding
private val checkedPositions = mutableSetOf<Int>()
class StepListAdapter(foods: Food) :
    RecyclerView.Adapter<StepListAdapter.StepViewHolder>() {
    private val steps = foods.steps
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_step_checkbox, parent, false)
        return StepViewHolder(view)
    }

    override fun getItemCount(): Int = steps.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        holder.apply {
            binding.checkBox.text = "Step #${position + 1}"
            binding.textStepDescription.text = "${steps[position]}"
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                   checkedPositions.add(position)
                } else {
                    checkedPositions.remove(position)
                }
            }

        }
    }
    fun getCheckedCount(): Int=checkedPositions.size

    class StepViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemStepCheckboxBinding.bind(viewItem)
    }

}