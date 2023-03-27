package com.example.masalafoodapplication.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.ActivityBaseBinding
import com.example.masalafoodapplication.ui.favourite.FavouriteFragment
import com.example.masalafoodapplication.ui.explore.ExploreFragment
import com.example.masalafoodapplication.ui.home.HomeFragment
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
                    setFragment(HomeFragment(), SetFragmentType.REPLACE, "Home")
                    true
                }

                R.id.nav_explore -> {
                    setFragment(ExploreFragment(), SetFragmentType.REPLACE, "Explore")
                    true
                }

                R.id.nav_favourite -> {
                    setFragment(FavouriteFragment(), SetFragmentType.REPLACE, "Fav")
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
        }}


        private fun initSubViews() {
        setFragment(HomeFragment(), SetFragmentType.ADD, "Home")
    }

    private fun setFragment(fragment: Fragment, setFragmentType: SetFragmentType, tag: String) {
        when (setFragmentType) {
            SetFragmentType.ADD -> addFragment(fragment)
            SetFragmentType.REPLACE -> replaceFragment(fragment, tag)
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            add(binding.fragmentContainer.id, fragment)
        }
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.commit {
            replace(binding.fragmentContainer.id, fragment, tag)
            setReorderingAllowed(true)
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    companion object {
        private const val CSV_NAME = "indian_food.csv"
    }

}