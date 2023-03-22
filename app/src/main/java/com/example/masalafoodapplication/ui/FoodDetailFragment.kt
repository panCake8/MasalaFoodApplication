package com.example.masalafoodapplication.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.kiko.fillapp.data.domain.Food


class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailBinding
        get() = FragmentFoodDetailBinding::inflate

    override fun setup() {
        var food = arguments?.getParcelable<Food>("KEY")
        chooseChips(food)
    }

    override fun onClicks() {
    }



    private fun chooseChips(food: Food?) {
        binding.chiceGroupChips.setOnCheckedChangeListener(object : ChipGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: ChipGroup, @IdRes checkedId: Int) {
                val chip: Chip? = group?.findViewById(checkedId)
                chip?.let {
                    Log.i("TAG", "chooseChips: ${it.text}")
                    binding.descriptionTv.text = food?.makeRecipe ?: "empty"
                    if (it.text == "Ingradient") {
                        binding.descriptionTv.text = food?.ingredients?.split(";")?.joinToString(separator ="\n")
                            ?: "empty"
                    } else if (it.text == "Steps") {
                        binding.descriptionTv.text = food?.ingredients?.split(";")?.joinToString(separator ="\n")
                            ?: "empty"
                    }
                }
            }
        })
    }
}

