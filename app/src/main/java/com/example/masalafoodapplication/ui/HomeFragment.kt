package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentHomeBinding
import com.kiko.fillapp.data.domain.Food
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        const val NUM_FOODS = 3
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun setup() {
        bindRandomKitchenImages()
        bindQuickRecipesData()
        bindJustForYouData()
        bindMakeYourMealImage()
    }

    override fun onClicks() {
        binding.apply {
            ivIndianFood.setOnClickListener {
                transitionToWithBackStack(HistoryFragment(), Constants.HISTORY)
            }
            ivKitchenAsian.setOnClickListener {
                val food = DataManager.getAllFood().filter {
                    it.cuisine ==
                            tvKitchen1.text.toString()
                }
                transform(DetailsKitchenFragment(Constants.ASIAN), Constants.DETAILS_KITCHEN)
                newInstance(food as ArrayList<Food>, Constants.ASIAN)
            }

            ivKitchenArabian.setOnClickListener {
                val food = DataManager.getAllFood().filter {
                    it.cuisine ==
                            tvKitchen2.text.toString()
                }
                transform(DetailsKitchenFragment(Constants.ARAB), Constants.DETAILS_KITCHEN)
                newInstance(food as ArrayList<Food>, Constants.ARAB)
            }

            ivKitchenIndian.setOnClickListener {
                val food = DataManager.getAllFood().filter {
                    it.cuisine ==
                            tvKitchen3.text.toString()
                }
                transform(DetailsKitchenFragment(Constants.INDIAN), Constants.DETAILS_KITCHEN)
                newInstance(food as ArrayList<Food>, Constants.INDIAN)
            }

            ivKitchenKashmir.setOnClickListener {
                val food = DataManager.getAllFood().filter {
                    it.cuisine ==
                            tvKitchen4.text.toString()
                }
                transform(DetailsKitchenFragment(Constants.KASHMIRI), Constants.DETAILS_KITCHEN)
                newInstance(food as ArrayList<Food>, Constants.KASHMIRI)
            }
        }
        moreOnClicks()
        cardQuickOnClicks()
        cardJustForYouOnClicks()
    }

    private fun moreOnClicks() {
        binding.apply {
            quickMore.setOnClickListener {
                transform(RecipesMenuFragment(), Constants.RECIPE_MENU)

            }
            justForYouMore.setOnClickListener {
                transform(RandomRecipesFragment(), Constants.RANDOM_RECIPE)
            }
            ivMakeYourMealArrow.setOnClickListener {
                transform(SuggestionFilterFragment(), Constants.SUGGESTION_FILTER)
                transitionToWithBackStack(SuggestionFilterFragment(), Constants.SUGGESTION_FILTER)
            }
        }
    }

    private fun transform(fragment: Fragment, name: String) {
        transitionToWithBackStack(
            fragment,
            name
        )
    }

    private fun cardQuickOnClicks() {
        binding.apply {
            ivQuickRecipe1.setOnClickListener {
                transform(FoodDetailFragment(), Constants.FOOD_DETAILS)
                newInstance(DataManager.getAllFood().first {
                    it.recipeName == tvQuickRecipe1Title.text
                        .toString()
                }, Constants.FOOD_DETAILS)
            }
            ivQuickRecipe2.setOnClickListener {
                transform(FoodDetailFragment(), Constants.FOOD_DETAILS)
                newInstance(DataManager.getAllFood().first {
                    it.recipeName == tvQuickRecipe2Title.text
                        .toString()
                }, Constants.FOOD_DETAILS)
            }
            ivQuickRecipe3.setOnClickListener {
                transform(FoodDetailFragment(), Constants.FOOD_DETAILS)
                newInstance(DataManager.getAllFood().first {
                    it.recipeName == tvQuickRecipe3Title.text
                        .toString()
                }, Constants.FOOD_DETAILS)
            }
        }
    }

    private fun cardJustForYouOnClicks() {
        binding.apply {
            ivJustForYou1.setOnClickListener {
                transform(FoodDetailFragment(), Constants.FOOD_DETAILS)
                newInstance(DataManager.getAllFood().first {
                    it.recipeName == tvJustForYou1Title.text
                        .toString()
                }, Constants.FOOD_DETAILS)
            }
            ivJustForYou2.setOnClickListener {
                transform(FoodDetailFragment(), Constants.FOOD_DETAILS)
                newInstance(DataManager.getAllFood().first {
                    it.recipeName == tvJustForYou2Title.text
                        .toString()
                }, Constants.FOOD_DETAILS)
            }
            ivJustForYou3.setOnClickListener {
                transform(FoodDetailFragment(), Constants.FOOD_DETAILS)
                newInstance(DataManager.getAllFood().first {
                    it.recipeName == tvJustForYou3Title.text
                        .toString()
                }, Constants.FOOD_DETAILS)
            }
        }
    }

    private fun bindQuickRecipesData() {
        val recipes = DataManager.getRandomQuickRecipes(NUM_FOODS)
        binding.apply {
            setImages(recipes, ivQuickRecipe1, ivQuickRecipe2, ivQuickRecipe3)
            setTitles(recipes, tvQuickRecipe1Title, tvQuickRecipe2Title, tvQuickRecipe3Title)
            setTimes(recipes, tvQuickRecipe1Time, tvQuickRecipe2Time, tvQuickRecipe3Time)
        }
    }

    private fun bindJustForYouData() {
        val foods = DataManager.getRandomFoods(NUM_FOODS)
        binding.apply {
            setImages(foods, ivJustForYou1, ivJustForYou2, ivJustForYou3)
            setTitles(foods, tvJustForYou1Title, tvJustForYou2Title, tvJustForYou3Title)
            setTimes(foods, tvJustForYou1Time, tvJustForYou2Time, tvJustForYou3Time)
        }
    }

    private fun bindRandomKitchenImages() {
        binding.apply {
            loadRandomImage(binding.ivKitchenAsian, Constants.ASIAN)
            loadRandomImage(binding.ivKitchenIndian, Constants.INDIAN)
            loadRandomImage(binding.ivKitchenArabian, Constants.ARAB)
            loadRandomImage(binding.ivKitchenKashmir, Constants.KASHMIRI)
        }
    }

    private fun bindMakeYourMealImage() {
        loadRandomImage(binding.ivMakeYourMeal, Constants.INDIAN)
    }


    private fun loadRandomImage(imageView: ImageView, cuisine: String) {
        imageView.loadImage(DataManager.getRandomImageUrlByCuisine(cuisine))
    }

    private fun setImages(
        foodList: List<Food>,
        imageView: ImageView,
        imageView2: ImageView,
        imageView3: ImageView
    ) {
        listOf(imageView, imageView2, imageView3)
            .forEachIndexed { index, imv ->
                imv.loadImage(foodList[index].imageUrl)
            }
    }

    private fun setTitles(
        foodList: List<Food>,
        textView: TextView,
        textView2: TextView,
        textVie3: TextView
    ) {
        listOf(textView, textView2, textVie3)
            .forEachIndexed { index, tv ->
                tv.text = foodList[index].recipeName
            }
    }

    private fun setTimes(
        foodList: List<Food>,
        textView: TextView,
        textView2: TextView,
        textVie3: TextView
    ) {
        listOf(textView, textView2, textVie3)
            .forEachIndexed { index, tv ->
                tv.setPreparationTime(foodList[index].timeMinutes)
            }
    }

}

