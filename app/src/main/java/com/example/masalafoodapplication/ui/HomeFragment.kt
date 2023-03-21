package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        const val NUM_FOODS = 3
        const val ARAB = "Arab"
        const val ASIAN = "Asian"
        const val INDIAN = "Indian"
        const val KASHMIRI = "Kashmiri"
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate


    override fun setup() {
        bindRandomKitchenImages()
        bindQuickRecipesData()
        bindJustForYouData()
        bindMakeYourMealImage()
    }

    override fun onClicks() {}

    private fun bindQuickRecipesData() {
        val recipes = DataManager.getRandomQuickRecipes(NUM_FOODS)
        binding.apply {
            listOf(
                ivQuickRecipe1,
                ivQuickRecipe2,
                ivQuickRecipe3
            ).forEachIndexed { index, imageView ->
                imageView.loadImage(recipes[index].imageUrl)
            }

            listOf(
                tvQuickRecipe1Title,
                tvQuickRecipe2Title,
                tvQuickRecipe3Title
            ).forEachIndexed { index, textView ->
                textView.text = recipes[index].recipeName
            }

            listOf(
                tvQuickRecipe1Time,
                tvQuickRecipe2Time,
                tvQuickRecipe3Time
            ).forEachIndexed { index, textView ->
                textView.setPreparationTime(recipes[index].timeMinutes)
            }
        }
    }

    private fun bindJustForYouData() {
        val foods = DataManager.getRandomFoods(NUM_FOODS)
        binding.apply {
            listOf(
                ivJustForYou1,
                ivJustForYou2,
                ivJustForYou3
            ).forEachIndexed { index, imageView ->
                imageView.loadImage(foods[index].imageUrl)
            }

            listOf(
                tvJustForYou1Title,
                tvJustForYou2Title,
                tvJustForYou3Title
            ).forEachIndexed { index, textView ->
                textView.text = foods[index].recipeName
            }

            listOf(
                tvJustForYou1Time,
                tvJustForYou2Time,
                tvJustForYou3Time
            ).forEachIndexed { index, textView ->
                textView.setPreparationTime(foods[index].timeMinutes)
            }
        }
    }

    private fun bindRandomKitchenImages() {
        binding.apply {
            ivKitchenAsian.loadImage(DataManager.getRandomImageUrlByCuisine(ASIAN))
            ivKitchenIndian.loadImage(DataManager.getRandomImageUrlByCuisine(INDIAN))
            ivKitchenArabian.loadImage(DataManager.getRandomImageUrlByCuisine(ARAB))
            ivKitchenKashmir.loadImage(DataManager.getRandomImageUrlByCuisine(KASHMIRI))
        }
    }

    private fun bindMakeYourMealImage() {
        binding.ivMakeYourMeal.loadImage(DataManager.getRandomImageUrlByCuisine(INDIAN))
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(this).load(url).placeholder(android.R.drawable.progress_horizontal).into(this)
    }

    private fun TextView.setPreparationTime(time: Int) {
        this.text = "Prep Time: $time mins"

    }

}