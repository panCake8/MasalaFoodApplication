package com.example.masalafoodapplication.ui.random_recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentRandomRecipesBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.random_recipes.adapters.RandomRecipesAdapter
import com.example.masalafoodapplication.ui.random_recipes.adapters.RandomRecipesInteractionListener
import com.example.masalafoodapplication.util.Constants


class RandomRecipesFragment : BaseFragment<FragmentRandomRecipesBinding>(),
    RandomRecipesInteractionListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomRecipesBinding
        get() = FragmentRandomRecipesBinding::inflate


    override fun setup() {
        val adapter = RandomRecipesAdapter(DataManager.getAllQuickRecipes(), this)
        binding.recyclerRandomRecipes.adapter = adapter
    }

    override fun onClicks() {
        binding.recipesMenuToolbar.setOnClickListener {
            onBack()
        }
    }

    override fun onClickRecipesCard(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.RANDOM_RECIPE)
    }


}