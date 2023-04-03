package com.example.masalafoodapplication.ui.food_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.base.HomeActivity
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        hideNaveBar()
        listenToFragmentResult()
        food?.let { bindData(it) }
    }

    private fun hideNaveBar() {
        (activity as HomeActivity).hideBottomNavBar()
    }

    fun onClicks() {
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
        if (dataManager.isFavorite(food)) {
            dataManager.deleteFavourite(food)
            Toast.makeText(requireContext(), getString(R.string.deleted), Toast.LENGTH_SHORT).show()
            binding.iconFavorite.setImageResource(R.drawable.ic_love_icon_new_version_outlined__2_)
        } else {
            dataManager.addFavourite(food)
            Toast.makeText(requireContext(), getString(R.string.added), Toast.LENGTH_SHORT).show()
            binding.iconFavorite.setImageResource(R.drawable.ic_love_icon_new_version)
        }
    }

    private fun chooseChips(food: Food?) {
        binding.chipsList.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.size != 0) {
                val chip: Chip? = group.findViewById(checkedIds[0])
                chip?.let {
                    if (it.text.toString() == getString(R.string.steps)) {
                        val adapter =
                            FoodDetailAdapter(food?.steps ?: listOf())
                        binding.recyclerStepsIngredientsItems.adapter = adapter
                        binding.chipIngredient.isClickable = true
                        binding.chipSteps.isClickable = false
                    } else if (it.text.toString() == getString(R.string.ingredient)) {
                        val adapter =
                            FoodDetailAdapter(food?.ingredientQuantities ?: listOf())
                        binding.recyclerStepsIngredientsItems.adapter = adapter
                        binding.chipIngredient.isClickable = false
                        binding.chipSteps.isClickable = true
                    }
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
            food = dataManager.getFoodById(recipeId)
            bindData(food!!)
        }
    }

    private fun bindData(recipe: Food) {
        if (dataManager.isFavorite(recipe)) {
            binding.iconFavorite.setImageResource(R.drawable.ic_love_icon_new_version)
        }
        binding.textDishName.text = recipe.recipeName
        binding.chipsList.check(R.id.ingredients)
        val adapter = FoodDetailAdapter(recipe.ingredientQuantities)
        binding.chipIngredient.isClickable = false
        binding.recyclerStepsIngredientsItems.adapter = adapter
        binding.imageBackground.loadImage(recipe.imageUrl)
        chooseChips(recipe)
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
