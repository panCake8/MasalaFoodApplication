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
import com.example.masalafoodapplication.data.domain.Food


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
            getIngredient(food)
        }
    }

    override fun onClicks() {
//        binding.topAppBar.setNavigationOnClickListener {
//            onBack()
//        }
        binding.nexBtn.setOnClickListener {
            parentFragmentManager.commit {
                parentFragmentManager.popBackStack()
                transitionToWithBackStack(StepsFragment(), Constants.STEPS)
                newInstance(food, Constants.STEPS)
            }
        }
    }

    private fun getIngredient(foods: Food?) {
        val options = foods?.ingredients?.split(";")?.toTypedArray()
        val linearLayoutOptions = binding.checkboxLayout
        for (option in options!!) {
            val checkBox = MaterialCheckBox(context)
            checkBox.id = View.generateViewId()
            checkBox.width = ViewGroup.LayoutParams.MATCH_PARENT
            checkBox.height = ViewGroup.LayoutParams.WRAP_CONTENT
            checkBox.text = option
            checkBox.layoutDirection = View.LAYOUT_DIRECTION_RTL
            checkBox.buttonTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.base_color
                )
            )
            checkBox.isChecked = false
            checkBox.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.text_medium)
            )
            checkBox.typeface = ResourcesCompat.getFont(binding.root.context, R.font.work_sans)
            linearLayoutOptions.addView(checkBox)
        }
    }


}