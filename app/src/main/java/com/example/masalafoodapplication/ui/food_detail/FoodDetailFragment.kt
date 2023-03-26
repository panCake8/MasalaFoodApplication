package com.example.masalafoodapplication.ui.food_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.Food
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.example.masalafoodapplication.ui.IngredientFragment
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding>() {
    private lateinit var food: Food
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailBinding
        get() = FragmentFoodDetailBinding::inflate

    override fun setup() {
        parentFragmentManager.setFragmentResultListener(
            Constants.FOOD_DETAILS, this
        ) { _, result ->
            food = result.getParcelable(Constants.FOOD_DETAILS)!!
            binding.dishName.text = food.recipeName ?: "not found"
            binding.GroupChips.check(R.id.description)
            val adapter = FoodDetailAdapter(food.ingredients.split(";").toList() ?: listOf("there is nothing here"))
            binding.ItemRecyclerView.adapter = adapter
            Glide.with(requireActivity()).load(food.imageUrl).into(binding.backgroundImage)
            chooseChips(food)

        }

    }

    override fun onClicks() {
        binding.foodDetailMenuToolbar.setOnClickListener {
            onBack()
        }
        binding.startButton.setOnClickListener {
            transitionToWithBackStack(IngredientFragment(), Constants.INGREDIENT)
            newInstance(food, Constants.INGREDIENT)
        }
    }




    private fun chooseChips(food: Food?) {
        binding.GroupChips.setOnCheckedChangeListener(object : ChipGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: ChipGroup, @IdRes checkedId: Int) {
                val chip: Chip? = group.findViewById(checkedId)
                chip?.let {
                    if (it.text.toString() == Cons.INGREDIENT) {
                        val adapter = FoodDetailAdapter(food?.ingredients?.split(";")?.toList() ?: listOf())
                        binding.ItemRecyclerView.adapter = adapter
                    } else if (it.text.toString() == Cons.STEPS) {
                        val adapter = FoodDetailAdapter(food?.makeRecipe?.split(";")?.toList() ?: listOf())
                        binding.ItemRecyclerView.adapter = adapter

                    } else if (it.text.toString() == Cons.DESCRIPTION){
                        val adapter = FoodDetailAdapter(food?.cleaned?.split(";")?.toList() ?: listOf())
                        binding.ItemRecyclerView.adapter = adapter
                    }
                }
            }
        })
    }

    object Cons {
        const val INGREDIENT = "Ingredient"
        const val STEPS = "Steps"
        const val DESCRIPTION = "Description"
    }
}
