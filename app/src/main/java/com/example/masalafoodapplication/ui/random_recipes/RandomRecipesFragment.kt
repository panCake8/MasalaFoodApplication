package com.example.masalafoodapplication.ui.random_recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentRandomRecipesBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.random_recipes.adapters.RandomRecipesAdapter


class RandomRecipesFragment: BaseFragment<FragmentRandomRecipesBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomRecipesBinding
        get() = FragmentRandomRecipesBinding::inflate


    override fun setup() {
        val adapter = RandomRecipesAdapter(DataManager.getAllFood())
        binding.recyclerRandomRecipes.adapter =adapter
    }
    override fun onClicks() {
        binding.recipesMenuToolbar.setNavigationOnClickListener{
            onBack()
        }
    }



}