package com.example.masalafoodapplication.ui.ingredient

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.enums.FoodDetaisType

import com.example.masalafoodapplication.databinding.FragmentIngredientBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.data.domain.models.FoodDetailsItem
import com.example.masalafoodapplication.ui.base.HomeActivity
import com.example.masalafoodapplication.ui.ingredient.adapter.IngredientAdapter
import com.example.masalafoodapplication.ui.ingredient.adapter.IngredientListAdapter
import com.example.masalafoodapplication.ui.steps.StepsFragment


class IngredientFragment : BaseFragment<FragmentIngredientBinding>() {
    private lateinit var food: Food
    private lateinit var ingredientListAdapter: IngredientListAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientBinding
        get() = FragmentIngredientBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        hideNaveBar()
        listenToFragmentResult()
    }
    private fun hideNaveBar() {
        (activity as HomeActivity).hideBottomNavBar()
    }
    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.INGREDIENT,
            this
        ) { _, result ->
            food = dataManager.getFoodById(result.getInt(Constants.INGREDIENT))
            val items = mutableListOf<FoodDetailsItem<Any>>()
            items.add(FoodDetailsItem("", FoodDetaisType.VIEW_TYPE_IMAGE))
            items.add(FoodDetailsItem("", FoodDetaisType.VIEW_TYPE_TEXT))
            items.add(FoodDetailsItem(food, FoodDetaisType.VIEW_TYPE_CHECKBOX))
            binding.recyclerCheckboxIngredient.adapter = IngredientAdapter(items)
            ingredientListAdapter= IngredientAdapter(items).getIngredientListAdapter(food)!!;
        }
    }


    fun onClicks() {
        binding.toolbarIngredient.setNavigationOnClickListener {
            onBack()
        }
        binding.buttonNext.setOnClickListener {
            if (!::ingredientListAdapter.isInitialized) {
                return@setOnClickListener
            }
            val checkedCount =  ingredientListAdapter.getCheckedCount()
            val totalCount =  ingredientListAdapter.itemCount
            if (checkedCount < totalCount) {
              Toast.makeText(requireContext(), "you should have all ingredient to make recipe", Toast.LENGTH_SHORT).show()
            } else {
                newInstance(food.id, Constants.KEY_FOOD_ID)
                transitionToWithBackStackAdd(
                    StepsFragment(),
                    this@IngredientFragment,
                    Constants.INGREDIENT
                )
          }
        }
    }

    private fun onBack() {
        requireActivity().onBackPressed()
        (activity as HomeActivity).showBottomNavBar()
    }

    private fun transitionToWithBackStackAdd(fragment: Fragment, fragment2: Fragment, tag: String) {
        parentFragmentManager.commit {
            add(R.id.fragment_container, fragment)
            addToBackStack(tag)
                .hide(fragment2)
            setReorderingAllowed(true)
        }
    }

    private fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }
}