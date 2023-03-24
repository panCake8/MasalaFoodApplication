package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentQuickRecipesBinding
import com.kiko.fillapp.data.domain.Food

class QuickRecipesFragment : BaseFragment<FragmentQuickRecipesBinding>() {
    private lateinit var list: List<Food>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuickRecipesBinding
        get() = FragmentQuickRecipesBinding::inflate


    override fun setup() {
        list = DataManager.showMostQuickRecipes()
        val adapter = RecipesMenuAdapter(DataManager.getAllFood())
        binding.recyclerQuickRecipe.adapter =adapter

    }
    override fun onClicks() {}


}