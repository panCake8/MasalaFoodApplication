package com.example.masalafoodapplication.ui.ingredient.adapter

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
import com.example.masalafoodapplication.databinding.ListIngrdientBinding
import com.example.masalafoodapplication.util.Constants


class IngredientAdapter(
    private val foodDetailsItem: List<FoodDetailsItem<Any>>
) :
    RecyclerView.Adapter<IngredientAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when (viewType) {
            R.layout.item_image_ingredient -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_image_ingredient, parent, false)
                return IngredientImageViewHolder(view)
            }
            R.layout.item_text_ingredient -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_text_ingredient, parent, false)
                return IngredientTextViewHolder(view)
            }
            R.layout.list_ingrdient -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_ingrdient, parent, false)
                return IngredientViewHolder(view)
            }
            else -> throw Exception(Constants.UNKNOWN_HOME_ITEM_TYPE)
        }
    }

    override fun getItemCount(): Int = foodDetailsItem.size

    override fun getItemViewType(position: Int): Int {
        return when (foodDetailsItem[position].type) {
            FoodDetaisType.VIEW_TYPE_IMAGE -> R.layout.item_image_ingredient
            FoodDetaisType.VIEW_TYPE_TEXT -> R.layout.item_text_ingredient
            FoodDetaisType.VIEW_TYPE_CHECKBOX -> R.layout.list_ingrdient
        }
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
    ) {
        when (holder) {
            is IngredientViewHolder -> holder.bind(foodDetailsItem[position])
            is IngredientImageViewHolder -> holder.bind(foodDetailsItem[position])
            is IngredientTextViewHolder -> holder.bind(foodDetailsItem[position])
        }
    }

    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        abstract fun bind(item: FoodDetailsItem<Any>)
    }

    class IngredientViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ListIngrdientBinding.bind(viewItem)
        override fun bind(item: FoodDetailsItem<Any>) {

            binding.apply {
                recyclerListIngredient.adapter = IngredientListAdapter(item.data as Food)

            }
        }
    }

    class IngredientImageViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemImageIngredientBinding.bind(viewItem)
        override fun bind(item: FoodDetailsItem<Any>) {
            binding.apply {
            }

        }
    }
    fun getIngredientListAdapter(food:Food): IngredientListAdapter {
        return IngredientListAdapter(food)
    }

    class IngredientTextViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemTextIngredientBinding.bind(viewItem)
        override fun bind(item: FoodDetailsItem<Any>) {

        }
    }
}