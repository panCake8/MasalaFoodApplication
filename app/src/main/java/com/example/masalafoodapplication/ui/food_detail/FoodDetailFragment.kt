package com.example.masalafoodapplication.ui.food_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.adapters.FoodDetailAdapter
import com.example.masalafoodapplication.ui.ingredient.IngredientFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.Constants.KEY_FOOD_ID
import com.example.masalafoodapplication.util.loadImage
import com.google.android.material.chip.Chip


class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding>() {
    private var food: Food? = null
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailBinding
        get() = FragmentFoodDetailBinding::inflate

    override fun setup() {
        listenToFragmentResult()
        food?.let { bindData(it) }
    }

    override fun onClicks() {
        binding.foodDetailMenuToolbar.setOnClickListener {
            onBack()
        }
        binding.startButton.setOnClickListener {
            newInstance(food!!.id, Constants.INGREDIENT)
            transitionToWithBackStackReplace(IngredientFragment(), Constants.FOOD_DETAILS)
        }
    }


    private fun chooseChips(food: Food?) {
        binding.GroupChips.setOnCheckedStateChangeListener { group, checkedIds ->
            val chip: Chip? = group.findViewById(checkedIds[0])
            chip?.let {
                if (it.text.toString() == Constants.STEPS) {
                    val adapter =
                        FoodDetailAdapter(food?.steps ?: listOf())
                    binding.ItemRecyclerView.adapter = adapter

                } else if (it.text.toString() == Constants.INGREDIENT) {
                    val adapter =
                        FoodDetailAdapter(food?.ingredient ?: listOf())
                    binding.ItemRecyclerView.adapter = adapter
                }
            }
        }
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            KEY_FOOD_ID,
            this
        ) { _, result ->
            val recipeId = result.getInt(KEY_FOOD_ID)
            food = DataManager.getFoodById(recipeId)
            bindData(food!!)
        }
    }

    private fun bindData(recipe: Food) {
        binding.dishName.text = recipe.recipeName
        binding.GroupChips.check(R.id.ingredients)
        val adapter = FoodDetailAdapter(recipe.ingredientQuantities)
        binding.ItemRecyclerView.adapter = adapter
        binding.backgroundImage.loadImage(recipe.imageUrl)
        chooseChips(recipe)
    }
}
