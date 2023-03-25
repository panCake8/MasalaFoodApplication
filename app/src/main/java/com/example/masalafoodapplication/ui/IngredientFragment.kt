package com.example.masalafoodapplication.ui

import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.FragmentIngredientBinding
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.checkbox.MaterialCheckBox
import com.kiko.fillapp.data.domain.Food


class IngredientFragment : BaseFragment<FragmentIngredientBinding>() {
    private lateinit var food: Food
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientBinding
        get() = FragmentIngredientBinding::inflate

    override fun setup() {
        parentFragmentManager.setFragmentResultListener(
            Constants.INGREDIENT,
            this
        ) { _, result ->
            food = result.getParcelable(Constants.INGREDIENT)!!
            val adapter=IngredientAdapter(food)
            binding.checkboxRecycler.adapter=adapter
        }
    }

    override fun onClicks() {
        binding.ingredientToolbar.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.nextBtn.setOnClickListener {
            parentFragmentManager.commit {
                parentFragmentManager.popBackStack()
                transitionToWithBackStack(StepsFragment(), Constants.STEPS)
                newInstance(food, Constants.STEPS)
            }
        }
    }



}