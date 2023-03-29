package com.example.masalafoodapplication.ui.steps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.enums.FoodDetaisType
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.FoodDetailsItem
import com.example.masalafoodapplication.databinding.ItemImageIngredientBinding
import com.example.masalafoodapplication.databinding.ItemTextIngredientBinding
import com.example.masalafoodapplication.databinding.ListStepIngredientBinding
import com.example.masalafoodapplication.util.Constants

class StepsAdapter(
    private val foodDetailsItem: List<FoodDetailsItem<Any>>
) : RecyclerView.Adapter<StepsAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            R.layout.item_image_step -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_image_step, parent, false)
                return StepImageViewHolder(view)
            }
            R.layout.item_text_step -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_text_step, parent, false)
                return StepTextViewHolder(view)
            }
            R.layout.list_step_ingredient -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_step_ingredient, parent, false)
                return StepViewHolder(view)
            }
            else -> throw Exception(Constants.UNKNOWN_HOME_ITEM_TYPE)
        }
    }

    override fun getItemCount(): Int = foodDetailsItem.size
    override fun getItemViewType(position: Int): Int {
        return when (foodDetailsItem[position].type) {
            FoodDetaisType.VIEW_TYPE_IMAGE -> R.layout.item_image_step
            FoodDetaisType.VIEW_TYPE_TEXT -> R.layout.item_text_step
            FoodDetaisType.VIEW_TYPE_CHECKBOX -> R.layout.list_step_ingredient
        }
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
    ) {
        when (holder) {
            is StepImageViewHolder -> holder.bind(foodDetailsItem[position])
            is StepTextViewHolder -> holder.bind(foodDetailsItem[position])
            is StepViewHolder -> holder.bind(foodDetailsItem[position])
        }
    }

    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        abstract fun bind(item: FoodDetailsItem<Any>)
    }

    class StepViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ListStepIngredientBinding.bind(viewItem)
        override fun bind(item: FoodDetailsItem<Any>) {
            binding.apply {
                recyclerListIngredientStep.adapter = StepListAdapter(item.data as Food)
            }
        }
    }

    class StepImageViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemImageIngredientBinding.bind(viewItem)
        override fun bind(item: FoodDetailsItem<Any>) {
            binding.apply {

            }

        }
    }

    class StepTextViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemTextIngredientBinding.bind(viewItem)
        override fun bind(item: FoodDetailsItem<Any>) {

        }
    }
}