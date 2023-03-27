package com.example.masalafoodapplication.ui.quick_recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentQuickRecipesBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.quick_recipes.adapters.QuickRecipesAdapter
import com.example.masalafoodapplication.ui.quick_recipes.adapters.QuickRecipesInteractionListener


class QuickRecipesFragment : BaseFragment<FragmentQuickRecipesBinding>(),
    QuickRecipesInteractionListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuickRecipesBinding
        get() = FragmentQuickRecipesBinding::inflate


    override fun setup() {
        val adapter = QuickRecipesAdapter(DataManager.getAllFood(), this)
        binding.recyclerQuickRecipe.adapter = adapter
    }

    override fun onClicks() {
        binding.recipesMenuToolbar.setNavigationOnClickListener {
            onBack()
        }
    }

    override fun onClickRecipesCard(food: Food) {

    }


}