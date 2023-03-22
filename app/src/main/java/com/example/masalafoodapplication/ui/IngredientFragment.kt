package com.example.masalafoodapplication.ui

import android.content.res.ColorStateList
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentIngredientBinding
import com.google.android.material.checkbox.MaterialCheckBox
import com.kiko.fillapp.data.domain.Food


class IngredientFragment : BaseFragment<FragmentIngredientBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentIngredientBinding
        get() = FragmentIngredientBinding::inflate

    override fun setup() {
        getIngredient(DataManager.getAllFood()[0])
    }

    override fun onClicks() {
        binding.topAppBar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.nexBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.fragment_container, IngredientFragment())
                    .addToBackStack("ingredient")
            }
        }
    }

    private fun getIngredient(foods: Food) {
        val options = foods.ingredients.split(";").toTypedArray()
        val linearLayoutOptions = binding.checkboxLayout
        for (option in options) {
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
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Log.d("TAG", "checked")
                } else {
                    Log.d("TAG", "non checked")
                }
            }
        }
    }


}