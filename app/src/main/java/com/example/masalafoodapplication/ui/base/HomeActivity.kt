package com.example.masalafoodapplication.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.data.DataManagerImpl
import com.example.masalafoodapplication.data.datasource.CsvDataSource
import com.example.masalafoodapplication.data.datasource.utils.CsvParser
import com.example.masalafoodapplication.databinding.ActivityBaseBinding
import com.example.masalafoodapplication.ui.explore.ExploreFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.favourite.FavouriteFragment
import com.example.masalafoodapplication.ui.home.HomeFragment
import com.example.masalafoodapplication.ui.ingredient.IngredientFragment
import com.example.masalafoodapplication.ui.steps.StepsFragment
import com.example.masalafoodapplication.ui.suggestion.SuggestionsFragment
import com.example.masalafoodapplication.ui.suggestionFilter.SuggestionFilterFragment


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    private lateinit var dataManager: DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        initDataManager(savedInstanceState)
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSubViews()
        setup()
        onClicks()
    }

    private fun setup() {

    }

    fun getDataManager(): DataManager {
        return dataManager
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

    private fun initDataManager(savedInstanceState: Bundle?) {
        dataManager = when (savedInstanceState) {
            null -> DataManagerImpl(CsvDataSource(CsvParser(), this))
            else -> savedInstanceState.getSerializable(KEY_DATA_MANAGER) as DataManager
        }
    }

    private fun clearBackStack() {
        supportFragmentManager.popBackStack(null, POP_BACK_STACK_INCLUSIVE)
    }

    override fun onBackPressed() {
        when (val currentFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)) {
            is HomeFragment -> finish()
            is ExploreFragment, is SuggestionFilterFragment, is FavouriteFragment -> {
                initSubViews()
                clearBackStack()
                binding.navBar.selectedItemId = R.id.nav_home
            }
            is FoodDetailFragment, is StepsFragment, is IngredientFragment, is SuggestionsFragment -> {
                supportFragmentManager.popBackStack()
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


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_DATA_MANAGER, dataManager)
    }

    companion object {
        const val KEY_DATA_MANAGER = "DATA_MANAGER"
        const val TAG_HOME_FRAGMENT = "Home"
        const val TAG_EXPLORE_FRAGMENT = "Explore"
        const val TAG_MAKE_MEAL_FRAGMENT = "MakeMeal"
        const val TAG_FAVOURITE_FRAGMENT = "Favorite"
    }

}

