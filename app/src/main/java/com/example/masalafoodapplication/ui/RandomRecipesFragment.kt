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
        var listFood =showMostQuickRecipes(DataManager.getAllFood())

        addImage(listFood)
        addPrepareTime(listFood)
        addRecipesName(listFood)

    }

    override fun onClicks() {
        binding.icArrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun showMostQuickRecipes(foodList: List<Food>) = foodList.shuffled()
    private  fun addImage(listFood:List<Food>){

        Glide.with(requireContext()).load( listFood[0].imageUrl).into(binding.image1)
        Glide.with(requireContext()).load( listFood[1].imageUrl).into(binding.image2)
        Glide.with(requireContext()).load( listFood[2].imageUrl).into(binding.image3)
        Glide.with(requireContext()).load( listFood[3].imageUrl).into(binding.image4)

    }
    private fun addRecipesName(listFood:List<Food>){
        binding.apply {
            recipeName1.text = listFood[0].recipeName
            recipeName2.text = listFood[1].recipeName
            recipeName3.text = listFood[2].recipeName
            recipeName4.text = listFood[3].recipeName



        }
    }
    private fun addPrepareTime(listFood:List<Food>){
        binding.apply {

            prepareTime1.text = listFood[0].timeMinutes.toString() +"m"
            prepareTime2.text = listFood[1].timeMinutes.toString() +"m"
            prepareTime3.text = listFood[2].timeMinutes.toString() +"m"
            prepareTime4.text = listFood[3].timeMinutes.toString() +"m"

        }
    }

}