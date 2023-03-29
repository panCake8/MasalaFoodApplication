package com.example.masalafoodapplication.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.ActivityBaseBinding
import com.example.masalafoodapplication.ui.favourite.FavouriteFragment
import com.example.masalafoodapplication.ui.explore.ExploreFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.home.HomeFragment
import com.example.masalafoodapplication.ui.ingredient.IngredientFragment
import com.example.masalafoodapplication.ui.steps.StepsFragment
import com.example.masalafoodapplication.ui.suggestionFilter.SuggestionFilterFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.CsvParser
import com.example.masalafoodapplication.util.SetFragmentType
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSubViews()
        setup()
        onClicks()
    }

    private fun setup() {
        openAndParseFile()
    }

    private fun onClicks() {
        binding.navBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    setFragment(HomeFragment(), "Home")
                    true
                }

                R.id.nav_explore -> {
                    setFragment(ExploreFragment(), "Explore")
                    true
                }

                R.id.nav_make_meal -> {
                    replaceFragment(SuggestionFilterFragment(), "MakeMeal")
                    true
                }

                R.id.nav_favourite -> {
                    setFragment(FavouriteFragment(), "Fav")
                    true
                }

                else -> false
            }
        }
    }

    private fun openAndParseFile() {
        val inputStream: InputStream = assets.open(CSV_NAME)
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val csvParser = CsvParser()
        var id = 0
        buffer.forEachLine { line ->
            val food = csvParser.parse(line, id)
            DataManager.addFood(food)
            id++
        }
    }


    private fun initSubViews() {
        setFragment(HomeFragment(), "Home")
    }

    private fun setFragment(fragment: Fragment, tag: String) {
        val currentFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        if (currentFragment == null || currentFragment.javaClass != fragment.javaClass)
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, fragment, tag)
                .addToBackStack(null)
                .commit()
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, fragment, tag)
            setReorderingAllowed(true)
        }
    }



    private fun clearBackStack() {
        supportFragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
        val topFragment = supportFragmentManager.fragments.lastOrNull()

        when (currentFragment) {
            is HomeFragment -> finish()
            is ExploreFragment, is SuggestionFilterFragment, is FavouriteFragment -> {
                initSubViews()
                clearBackStack()
                binding.navBar.selectedItemId = R.id.nav_home
            }
            is FoodDetailFragment, is StepsFragment, is IngredientFragment -> {
                supportFragmentManager.popBackStack()
                if (topFragment is ExploreFragment) {
                    // Change the selected item on the navigation bar to the home icon
                    initSubViews()
                    binding.navBar.selectedItemId = R.id.nav_home
                }
            }
            else -> {
                supportFragmentManager.popBackStackImmediate()
                val tag = currentFragment?.tag
                binding.navBar.selectedItemId = getSelectedItemId(tag)
            }
        }
    }
    private fun getSelectedItemId(tag: String?): Int {
        return when (tag) {
            "Home" -> R.id.nav_home
            "Explore" -> R.id.nav_explore
            "MakeMeal" -> R.id.nav_make_meal
            "Fav" -> R.id.nav_favourite
            else -> R.id.nav_home
        }
    }

    companion object {
        private const val CSV_NAME = "indian_food.csv"
    }

}