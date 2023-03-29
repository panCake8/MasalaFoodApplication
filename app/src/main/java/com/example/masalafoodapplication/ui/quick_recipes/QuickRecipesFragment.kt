package com.example.masalafoodapplication.ui.quick_recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentQuickRecipesBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.quick_recipes.adapters.QuickRecipesAdapter
import com.example.masalafoodapplication.ui.quick_recipes.adapters.QuickRecipesInteractionListener
import com.example.masalafoodapplication.util.Constants


class QuickRecipesFragment : BaseFragment<FragmentQuickRecipesBinding>(),
    QuickRecipesInteractionListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuickRecipesBinding
        get() = FragmentQuickRecipesBinding::inflate


    override fun setup() {
        val adapter = QuickRecipesAdapter(DataManager.getAllRandomQuickRecipes(), this)
        binding.recyclerQuickRecipe.adapter = adapter
    }

    override fun onClicks() {
        binding.quickRecipeToolbar.setOnClickListener {
            onBack()
        }
    }

    override fun onClickRecipesCard(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.QUICK_RECIPES)
    }
}