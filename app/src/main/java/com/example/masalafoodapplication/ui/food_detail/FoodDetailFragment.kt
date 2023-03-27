package com.example.masalafoodapplication.ui.food_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.adapters.FoodDetailAdapter
import com.example.masalafoodapplication.ui.ingredient.IngredientFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.Constants.KEY_FOOD_ID
import com.example.masalafoodapplication.util.Constants.TAG_INGREDIENT
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding>() {
    private lateinit var food: Food
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailBinding
        get() = FragmentFoodDetailBinding::inflate

    override fun setup() {
        listenToFragmentResult()

//        parentFragmentManager.setFragmentResultListener(
//            KEY_FOOD_ID, this
//        ) { _, result ->
//            food = getFoodById(result.getInt(KEY_FOOD_ID))
//            binding.dishName.text = food.recipeName ?: "not found"
//            binding.GroupChips.check(R.id.description)
//            val adapter =
//                FoodDetailAdapter(food.ingredientQuantities ?: listOf("there is nothing here"))
//            binding.ItemRecyclerView.adapter = adapter
//            Glide.with(requireActivity()).load(food.imageUrl).into(binding.backgroundImage)
//            chooseChips(food)
//        }
    }

    private fun getFoodById(id: Int): Food {
        return DataManager.getFoodById(id)
    }

    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            KEY_FOOD_ID, this
        ) { _, result ->
            food = getFoodById(result.getInt(KEY_FOOD_ID))
            bindData()
        }
    }

    private fun bindData() {
        binding.dishName.text = food.recipeName
        binding.GroupChips.check(R.id.description)
        val adapter = FoodDetailAdapter(food.ingredientQuantities)
        binding.ItemRecyclerView.adapter = adapter
        Glide.with(requireActivity()).load(food.imageUrl).into(binding.backgroundImage)
        chooseChips(food)
    }

    override fun onClicks() {
        binding.foodDetailMenuToolbar.setOnClickListener {
            onBack()
        }
        binding.startButton.setOnClickListener {
            transitionToWithBackStack(IngredientFragment(), TAG_INGREDIENT)
            newInstance(food, Constants.INGREDIENT)
        }
    }


    private fun chooseChips(food: Food?) {
        binding.GroupChips.setOnCheckedChangeListener(object : ChipGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: ChipGroup, @IdRes checkedId: Int) {
                val chip: Chip? = group.findViewById(checkedId)
                chip?.let {
                    if (it.text.toString() == Cons.INGREDIENT) {
                        val adapter = FoodDetailAdapter(
                            food?.ingredientQuantities ?: listOf()
                        )
                        binding.ItemRecyclerView.adapter = adapter
                    } else if (it.text.toString() == Cons.STEPS) {
                        val adapter =
                            FoodDetailAdapter(food?.steps ?: listOf())
                        binding.ItemRecyclerView.adapter = adapter

                    } else if (it.text.toString() == Cons.DESCRIPTION) {
                        val adapter =
                            FoodDetailAdapter(food?.ingredient ?: listOf())
                        binding.ItemRecyclerView.adapter = adapter
                    }
                }
            }
        })
    }

    object Cons {
        const val INGREDIENT = "Ingredient"
        const val STEPS = "Steps"
        const val DESCRIPTION = "Description"
    }
}
