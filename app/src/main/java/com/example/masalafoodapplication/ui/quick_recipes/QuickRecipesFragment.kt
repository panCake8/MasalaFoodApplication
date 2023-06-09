package com.example.masalafoodapplication.ui.quick_recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        val adapter = QuickRecipesAdapter(dataManager.getAllQuickRecipes(), this)
        binding.recyclerQuickRecipe.adapter = adapter
    }

    fun onClicks() {
        binding.quickRecipeToolbar.setNavigationOnClickListener {
            onBack()
        }
    }

    override fun onClickRecipesCard(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.QUICK_RECIPES)
    }

    private fun onBack() {
        requireActivity().onBackPressed()
    }

    private fun transitionToWithBackStackReplace(fragment: Fragment, tag: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }

    private fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }
}