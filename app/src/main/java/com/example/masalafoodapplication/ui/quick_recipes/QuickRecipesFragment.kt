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
    private lateinit var list: List<Food>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuickRecipesBinding
        get() = FragmentQuickRecipesBinding::inflate


    override fun setup() {
        list = DataManager.getAllFood()
        val adapter = QuickRecipesAdapter(DataManager.getAllFood(),this)
        binding.recyclerQuickRecipe.adapter =adapter
        binding.recipesMenuToolbar.setNavigationOnClickListener{
            onBack()
        }

    }
    override fun onClicks() {

    }


}