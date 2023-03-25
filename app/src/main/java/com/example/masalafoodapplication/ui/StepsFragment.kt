package com.example.masalafoodapplication.ui


import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.FragmentStepsBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.google.android.material.checkbox.MaterialCheckBox
import com.example.masalafoodapplication.data.domain.Food


class StepsFragment : BaseFragment<FragmentStepsBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStepsBinding
        get() = FragmentStepsBinding::inflate

    override fun setup() {
        parentFragmentManager.setFragmentResultListener(
            Constants.STEPS,
            this
        ) { _, result ->
            getSteps(result.getParcelable(Constants.STEPS))
        }
    }

    override fun onClicks() {
//        binding.topAppBar.setNavigationOnClickListener {
//            parentFragmentManager.popBackStack(Constants.FOOD_DETAILS, 0)
//        }
        binding.finishBtn.setOnClickListener {
            parentFragmentManager.popBackStack(Constants.FOOD_DETAILS, POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun getSteps(foods: Food?) {
        val options = foods?.makeRecipe?.split(";")?.toTypedArray()
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