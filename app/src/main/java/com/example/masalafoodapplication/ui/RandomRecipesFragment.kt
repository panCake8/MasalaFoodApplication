package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentRandomRecipesBinding
import com.example.masalafoodapplication.ui.adapter.RandomRecipesAdapter
import com.kiko.fillapp.data.domain.Food

class RandomRecipesFragment: BaseFragment<FragmentRandomRecipesBinding>() {

    private lateinit var list: List<Food>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomRecipesBinding
        get() = FragmentRandomRecipesBinding::inflate


    override fun setup() {
        list = DataManager.showMostQuickRecipes()
        val adapter = RandomRecipesAdapter(DataManager.getAllFood())
        binding.recyclerRandomRecipes.adapter =adapter
        binding.recipesMenuToolbar.setNavigationOnClickListener{
            onBack()
        }

    }
    override fun onClicks() {
    }



}