package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentRandomRecipesBinding
import com.kiko.fillapp.data.domain.Food

class RandomRecipesFragment: BaseFragment<FragmentRandomRecipesBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomRecipesBinding
        get() = FragmentRandomRecipesBinding::inflate


    override fun setup() {

        addImage()
        addPrepareTime()
        addRecipesName()

    }

    override fun onClicks() {
        binding.icArrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun showMostQuickRecipes(foodList: List<Food>) = foodList.sortedBy {
        it.timeMinutes
    }.take(8)
    private  fun addImage(){
        Glide.with(requireContext())
            .load( showMostQuickRecipes(DataManager.getAllFood())[0].imageUrl)
            .into(binding.image1)
        Glide.with(requireContext())
            .load( showMostQuickRecipes(DataManager.getAllFood())[1].imageUrl)
            .into(binding.image2)
        Glide.with(requireContext())
            .load( showMostQuickRecipes(DataManager.getAllFood())[2].imageUrl)
            .into(binding.image3)
        Glide.with(requireContext())
            .load( showMostQuickRecipes(DataManager.getAllFood())[3].imageUrl)
            .into(binding.image4)
        Glide.with(requireContext())
            .load( showMostQuickRecipes(DataManager.getAllFood())[4].imageUrl)
            .into(binding.image5)
        Glide.with(requireContext())
            .load( showMostQuickRecipes(DataManager.getAllFood())[5].imageUrl)
            .into(binding.image6)
    }
    private fun addRecipesName(){
        binding.apply {
            recipeName1.text =showMostQuickRecipes(DataManager.getAllFood())[0].recipeName
            recipeName2.text =showMostQuickRecipes(DataManager.getAllFood())[1].recipeName
            recipeName3.text =showMostQuickRecipes(DataManager.getAllFood())[2].recipeName
            recipeName4.text =showMostQuickRecipes(DataManager.getAllFood())[3].recipeName
            recipeName5.text =showMostQuickRecipes(DataManager.getAllFood())[4].recipeName
            recipeName6.text =showMostQuickRecipes(DataManager.getAllFood())[5].recipeName

        }
    }
    private fun addPrepareTime(){
        binding.apply {
            prepareTime1.text =showMostQuickRecipes(DataManager.getAllFood())[0]
                .timeMinutes.toString()+"m"
            prepareTime2.text =showMostQuickRecipes(DataManager.getAllFood())[1]
                .timeMinutes.toString()+"m"
            prepareTime3.text =showMostQuickRecipes(DataManager.getAllFood())[2]
                .timeMinutes.toString()+"m"
            prepareTime4.text =showMostQuickRecipes(DataManager.getAllFood())[3]
                .timeMinutes.toString()+"m"
            prepareTime5.text =showMostQuickRecipes(DataManager.getAllFood())[4]
                .timeMinutes.toString()+"m"
            prepareTime6.text =showMostQuickRecipes(DataManager.getAllFood())[5]
                .timeMinutes.toString()+"m"


        }
    }

}