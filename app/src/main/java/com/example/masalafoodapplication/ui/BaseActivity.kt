package com.example.masalafoodapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.ActivityBaseBinding
import com.example.masalafoodapplication.util.CsvParser
import com.example.masalafoodapplication.util.SetFragmentType
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class BaseActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private val exploreFragment = ExploreFragment()
    private val favouriteFragment = FavouriteFragment()
    private val suggestionsFragment = SuggestionsFragment()

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
                    setFragment(HomeFragment(), SetFragmentType.REPLACE)
                    true
                }
                R.id.nav_explore -> {
                    setFragment(ExploreFragment(), SetFragmentType.REPLACE)
                    true
                }
                R.id.nav_favourite -> {
                    setFragment(FavouriteFragment(), SetFragmentType.REPLACE)
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
        buffer.forEachLine { line ->
            val food = csvParser.parse(line)
            DataManager.addFood(food)
        }

    }

    private fun initSubViews() {
        setFragment(HomeFragment(), SetFragmentType.ADD)
    }

    private fun setFragment(fragment: Fragment, setFragmentType: SetFragmentType) {
        when (setFragmentType) {
            SetFragmentType.ADD -> addFragment(fragment)
            SetFragmentType.REPLACE -> replaceFragment(fragment)
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, fragment)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, fragment)
            setReorderingAllowed(true)
        }
    }

    companion object {
        private const val CSV_NAME = "indian_food.csv"
    }

}