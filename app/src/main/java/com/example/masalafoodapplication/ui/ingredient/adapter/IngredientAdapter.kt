package com.example.masalafoodapplication.ui.ingredient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.enums.FoodDetaisType
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.FoodDetailsItem
import com.example.masalafoodapplication.data.domain.models.HomeItem
import com.example.masalafoodapplication.databinding.FragmentIngredientBinding
import com.example.masalafoodapplication.databinding.ItemImageIngredientBinding
import com.example.masalafoodapplication.databinding.ItemStepIngredientBinding
import com.example.masalafoodapplication.databinding.ItemTextIngredientBinding
import com.example.masalafoodapplication.ui.ingredient.IngredientFragment


class IngredientAdapter(
    private val foodDetailsItem: List<FoodDetailsItem<Any>>,
    ingredientFragment: IngredientFragment
) :
    RecyclerView.Adapter<IngredientAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int):BaseViewHolder{
        when(viewType){
            R.layout.item_image_ingredient -> {
                val view=LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_image_ingredient,parent,false)
                return IngredientImageViewHolder(view)
            }
            R.layout.item_text_ingredient  ->{
                val view=LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_text_ingredient,parent,false)
                return IngredientTextViewHolder(view)
            }
            R.layout.item_step_ingredient -> {
                val view=LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_step_ingredient,parent,false)
                return IngredientViewHolder(view)
            }
        }
        return  super.createViewHolder(parent,viewType)

    }
     override fun getItemCount():Int=foodDetailsItem.size
    override fun getItemViewType(position: Int): Int {
        return when (foodDetailsItem[position].type) {
            FoodDetaisType.VIEW_TYPE_IMAGE -> R.layout.item_image_ingredient
            FoodDetaisType.VIEW_TYPE_TEXT->R.layout.item_text_ingredient
            FoodDetaisType.VIEW_TYPE_CHECKBOX->R.layout.item_step_ingredient
        }
    }
    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
    ) {
        when(holder){
            is IngredientViewHolder->holder.bind(foodDetailsItem[position])
            is IngredientImageViewHolder -> holder.apply {  }
            is IngredientTextViewHolder -> holder.apply{}
        }
    }
  abstract  class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
      abstract fun bind(item: FoodDetailsItem<Any>)
  }

    class IngredientViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemStepIngredientBinding.bind(viewItem)
      override  fun bind(foodDetailsItem: FoodDetailsItem<Any>) {
          binding.apply {
              checkBox.text="efewf"
//                recyclerCheckboxIngredient.adapter= IngredientListAdapter(foodDetailsItem.data as Food )
          }
        }
    }
    class IngredientImageViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemImageIngredientBinding.bind(viewItem)
        override fun bind(item: FoodDetailsItem<Any>) {
            binding.apply {

            }
            TODO("Not yet implemented")
        }
    }
    class IngredientTextViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemTextIngredientBinding.bind(viewItem)
        override fun bind(item: FoodDetailsItem<Any>) {
            TODO("Not yet implemented")
        }
    }
}