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
        binding.toolbar.setNavigationOnClickListener {
            onBack()
        }
        binding.iconFavorite.setOnClickListener {
            food?.let { it1 -> favoriteIcon(it1) }
        }
        binding.buttonStart.setOnClickListener {
            newInstance(food!!.id, Constants.INGREDIENT)
            transitionToWithBackStackReplace(IngredientFragment(), Constants.FOOD_DETAILS)
        }
    }

    private fun favoriteIcon(food: Food) {
        if (DataManager.isFavorite(food)) {
            DataManager.deleteFavourite(food)
            binding.iconFavorite.setImageResource(R.drawable.ic_love_icon_white)
        } else {
            DataManager.addFavourite(food)
            binding.iconFavorite.setImageResource(R.drawable.ic_love_icon)
        }
    }

    private fun chooseChips(food: Food?) {
        binding.chipsList.setOnCheckedStateChangeListener { group, checkedIds ->
            val chip: Chip? = group.findViewById(checkedIds[0])
            chip?.let {
                if (it.text.toString() == Constants.STEPS) {
                    val adapter =
                        FoodDetailAdapter(food?.steps ?: listOf())
                    binding.recyclerStepsIngredientsItems.adapter = adapter

                } else if (it.text.toString() == Constants.INGREDIENT) {
                    val adapter =
                        FoodDetailAdapter(food?.ingredient ?: listOf())
                    binding.recyclerStepsIngredientsItems.adapter = adapter
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
        binding.textDishname.text = recipe.recipeName
        binding.chipsList.check(R.id.ingredients)
        val adapter = FoodDetailAdapter(recipe.ingredientQuantities)
        binding.recyclerStepsIngredientsItems.adapter = adapter
        binding.imageBackground.loadImage(recipe.imageUrl)
        chooseChips(recipe)
    }
}
