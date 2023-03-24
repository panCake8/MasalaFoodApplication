package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentSuggestionsBinding
import com.example.masalafoodapplication.util.Constants
import com.kiko.fillapp.data.domain.Food

class SuggestionsFragment : BaseFragment<FragmentSuggestionsBinding>() {
    private lateinit var foodList: ArrayList<Food>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionsBinding
        get() = FragmentSuggestionsBinding::inflate

    override fun setup() {
        val data = listOf("salt", "amchur (dry mango powder)", "karela (bitter gourd pavakkai)")
        val breakfastAfterFilter = breakfastAndDinner(data)
        displayBreakfastDataCard(breakfastAfterFilter)

        displayDinnerDataFirstCard(breakfastAfterFilter[0])
        displayDinnerDataSecondCard(breakfastAfterFilter[1])

        val lunchAfterFilter = lunch(data)
        displayLunchDataFirstCard(lunchAfterFilter[0])
        displayLunchDataSecondCard(lunchAfterFilter[1])

        parentFragmentManager.setFragmentResultListener(
            Constants.SUGGESTIONS, this
        ) { _, result ->
            foodList = result.getParcelableArrayList(Constants.SUGGESTIONS)!!
        }

    }


    override fun onClicks() {
//        binding.sugTbTopAppBar.setOnClickListener {
//            onBack()
//        }
        cardOnClick()
    }

    private fun breakfastAndDinner(recipes: List<String>) =
        searchFoodAccordingSpecifications(recipes).sortedBy { it.timeMinutes }

    private fun lunch(recipes: List<String>) = searchFoodAccordingSpecifications(recipes)


    private fun searchFoodAccordingSpecifications(recipes: List<String>) =
        DataManager.getAllFood().filter { it.cleaned.split(";").containsAll(recipes) }


    private fun displayBreakfastDataCard(breakfast: List<Food>) {
        Glide
            .with(requireActivity().application.baseContext)
            .load(breakfast[0].imageUrl).into(binding.sugIvBreakImageFood1)

        Glide
            .with(requireActivity().application.baseContext)
            .load(breakfast[1].imageUrl)
            .into(binding.sugIvBreakImageFood2)

        binding.apply {
            sugTvBreakNameFood1.text = breakfast[0].recipeName.toString()
            sugTvBreakDetails1.text = breakfast[0].timeMinutes.toString()

            sugTvBreakNameFood2.text = breakfast[1].recipeName.toString()
            sugTvBreakDetails2.text = breakfast[1].timeMinutes.toString()
        }
    }

    private fun displayLunchDataFirstCard(lunch: Food) {
        Glide
            .with(requireActivity().application.baseContext)
            .load(lunch.imageUrl).into(binding.sugIvLunchImageFood1)
        binding.sugTvLunchNameFood1.text = lunch.recipeName.toString()
        binding.sugTvLunchDetails1.text = lunch.timeMinutes.toString()
    }

    private fun displayLunchDataSecondCard(lunch: Food) {
        Glide
            .with(requireActivity().application.baseContext)
            .load(lunch.imageUrl)
            .into(binding.sugIvLunchImageFood2)
        binding.sugTvLunchNameFood2.text = lunch.recipeName.toString()
        binding.sugTvLunchDetails2.text = lunch.timeMinutes.toString()
    }

    private fun displayDinnerDataFirstCard(dinner: Food) {
        Glide
            .with(requireActivity().application.baseContext)
            .load(dinner.imageUrl).into(binding.sugIvDinnerImageFood1)
        binding.sugTvDinnerNameFood1.text = dinner.recipeName.toString()
        binding.sugTvDinnerDetails1.text = dinner.timeMinutes.toString()
    }

    private fun displayDinnerDataSecondCard(dinner: Food) {
        Glide
            .with(requireActivity().application.baseContext)
            .load(dinner.imageUrl)
            .into(binding.sugIvDinnerImageFood2)
        binding.sugTvDinnerNameFood2.text = dinner.recipeName.toString()
        binding.sugTvDinnerDetails2.text = dinner.timeMinutes.toString()
    }

    private fun cardOnClick() {
        binding.apply {
            sugIvBreakImageFood1.setOnClickListener {
                transform(FoodDetailFragment(), Constants.SUGGESTIONS)
                newInstance(foodList[0], Constants.SUGGESTIONS)
            }

            sugIvBreakImageFood2.setOnClickListener {
                transform(FoodDetailFragment(), Constants.SUGGESTIONS)
                newInstance(foodList[1], Constants.SUGGESTIONS)
            }

            sugIvDinnerImageFood1.setOnClickListener {
                transform(FoodDetailFragment(), Constants.SUGGESTIONS)
                newInstance(foodList[2], Constants.SUGGESTIONS)
            }

            sugIvDinnerImageFood2.setOnClickListener {
                transform(FoodDetailFragment(), Constants.SUGGESTIONS)
                newInstance(foodList[3], Constants.SUGGESTIONS)
            }

            sugIvLunchImageFood1.setOnClickListener {
                transform(FoodDetailFragment(), Constants.SUGGESTIONS)
                newInstance(foodList[4], Constants.SUGGESTIONS)
            }

            sugIvLunchImageFood2.setOnClickListener {
                transform(FoodDetailFragment(), Constants.SUGGESTIONS)
                newInstance(foodList[5], Constants.SUGGESTIONS)
            }
        }
    }

    private fun transform(fragment: Fragment, name: String) {
        transitionToWithBackStack(
            fragment,
            name
        )
    }

}