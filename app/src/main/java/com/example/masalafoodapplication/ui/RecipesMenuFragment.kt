package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentRecipesMenuBinding

class RecipesMenuFragment : BaseFragment<FragmentRecipesMenuBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipesMenuBinding
        get() = FragmentRecipesMenuBinding::inflate


    override fun setup() {
        val adapter = RecipesMenuAdapter(DataManager.getAllFood())
        binding.recyclerRecipes.adapter =adapter

    }

    override fun onClicks() {
        binding.icArrowBack.setOnClickListener {
            onBack()
        }
    }



}