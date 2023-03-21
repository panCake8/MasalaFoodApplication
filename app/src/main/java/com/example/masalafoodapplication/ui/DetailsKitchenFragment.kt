package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentDetailsKitchenBinding
import com.kiko.fillapp.data.domain.Food


class DetailsKitchenFragment : BaseFragment<FragmentDetailsKitchenBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsKitchenBinding
        get() = FragmentDetailsKitchenBinding::inflate

    override fun setup() {
        val list = showData(DataManager.getAllFood(), "Fusion")
        Glide.with(requireContext()).load(list[0].imageUrl).into(binding.imgOne)
        binding.titleOne.text = list[0].Cuisine
        binding.infoOne.text = list[0].timeMinutes.toString()
    }

    override fun onClicks() {

    }

    private fun showData(foodList: List<Food>, kitchenName: String) = foodList.filter {
        it.Cuisine == kitchenName
    }

}