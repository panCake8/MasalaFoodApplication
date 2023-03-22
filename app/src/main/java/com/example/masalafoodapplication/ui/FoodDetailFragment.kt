package com.example.masalafoodapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.databinding.DataBindingUtil.setContentView
import com.example.masalafoodapplication.databinding.FragmentFoodDetailBinding
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.kiko.fillapp.data.domain.Food


class FoodDetailFragment : BaseFragment<FragmentFoodDetailBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFoodDetailBinding
        get() = FragmentFoodDetailBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonClicked()
    }

    override fun setup() {
        binding.chip.setOnClickListener {
            Toast.makeText(requireContext(), binding.chip.text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClicks() {
        chooseChips()
        buttonClicked()
    }

    private fun buttonClicked() {
        binding.d.setOnClickListener {
            Toast.makeText(requireContext(), binding.chip.text, Toast.LENGTH_SHORT).show()
        }
    }

    private fun chooseChips() {
        binding.chiceGroupChips.setOnCheckedChangeListener(object : ChipGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: ChipGroup, @IdRes checkedId: Int) {
                val chip: Chip? = group?.findViewById(checkedId)
                chip?.let {
                    Log.i("TAG", "chooseChips: ${it.text}")
                    binding.descriptionTv.text = it.text
                    if (it.text == "Ingradient") {
                        binding.descriptionTv.text = it.text
                    } else if (it.text == "Steps") {
                        binding.descriptionTv.text = it.text
                    }
                }
            }
        })
    }
}

