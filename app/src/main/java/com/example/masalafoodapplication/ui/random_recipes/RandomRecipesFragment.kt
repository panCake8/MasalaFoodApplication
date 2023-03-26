package com.example.masalafoodapplication.ui.random_recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentRandomRecipesBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.random_recipes.adapters.RandomRecipesAdapter


class RandomRecipesFragment: BaseFragment<FragmentRandomRecipesBinding>() {

    private lateinit var list: List<Food>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomRecipesBinding
        get() = FragmentRandomRecipesBinding::inflate


    override fun setup() {
        list = DataManager.getAllFood()
        val adapter = RandomRecipesAdapter(DataManager.getAllFood())
        binding.recyclerRandomRecipes.adapter =adapter
        binding.recipesMenuToolbar.setNavigationOnClickListener{
            onBack()
        }

    }
    override fun onClicks() {
    }



}