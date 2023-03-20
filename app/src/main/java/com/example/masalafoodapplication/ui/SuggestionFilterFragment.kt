package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.FragmentSuggestionFilterBinding
import com.example.masalafoodapplication.util.SetFragmentType


class SuggestionFilterFragment : BaseFragment<FragmentSuggestionFilterBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionFilterBinding
        get() = FragmentSuggestionFilterBinding::inflate
    private var pageCounter:Int = 1

    override fun setup() {

    }

    override fun onClicks() {
        binding.buttonNext.setOnClickListener{
            when (pageCounter) {
                1 -> {
                    binding.navbar.title = "Dinner"
                    binding.imageChief.setImageResource(R.drawable.red_chief_image)
                    pageCounter++
                }
                2 -> {
                    binding.navbar.title = "Lunch"
                    binding.imageChief.setImageResource(R.drawable.chief_image)
                    pageCounter++
                }
                3 -> {

                }
            }

        }

    }


}