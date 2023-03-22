package com.example.masalafoodapplication.ui

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentSuggestionsBinding
import com.example.masalafoodapplication.util.CsvParser
import com.kiko.fillapp.data.domain.Food
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL

class SuggestionsFragment : BaseFragment<FragmentSuggestionsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSuggestionsBinding
        get() = FragmentSuggestionsBinding::inflate

    override fun setup() {
        val data=listOf("salt", "amchur (dry mango powder)", "karela (bitter gourd pavakkai)")
        val breakfastAfterFilter=breakfastAndDinner(data)
        displayBreakfastDataCard(breakfastAfterFilter)

        displayDinnerDataFirstCard(breakfastAfterFilter[0])
        displayDinnerDataSecondCard(breakfastAfterFilter[1])

        val lunchAfterFilter=lunch(data)
        displayLunchDataFirstCard(lunchAfterFilter[0])
        displayLunchDataSecondCard(lunchAfterFilter[1])







    }

    override fun onClicks() {
        binding.sugTbTopAppBar.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun breakfastAndDinner(recipes:List<String>) =
        searchFoodAccordingSpecifications(recipes).sortedBy { it.timeMinutes }

    private fun lunch(recipes:List<String>) =searchFoodAccordingSpecifications(recipes)


    private fun searchFoodAccordingSpecifications(recipes:List<String>) =
        DataManager.getAllFood().filter { it.cleaned.split(";").containsAll(recipes)}


    private fun displayBreakfastDataCard(breakfast: List<Food>){
        Glide
            .with(requireActivity().application.baseContext)
            .load(breakfast[0].imageUrl).
            into(binding.sugIvBreakImageFood1)

        Glide
            .with(requireActivity().application.baseContext)
            .load(breakfast[1].imageUrl)
            .into(binding.sugIvBreakImageFood2)

        binding.apply {
            sugTvBreakNameFood1.text=breakfast[0].recipeName.toString()
            sugTvBreakDetails1.text=breakfast[0].timeMinutes.toString()

            sugTvBreakNameFood2.text=breakfast[1].recipeName.toString()
            sugTvBreakDetails2.text=breakfast[1].timeMinutes.toString()
        }
    }

    private fun displayLunchDataFirstCard(lunch: Food){
        Glide
            .with(requireActivity().application.baseContext)
            .load(lunch.imageUrl).
            into(binding.sugIvLunchImageFood1)
        binding.sugTvLunchNameFood1.text=lunch.recipeName.toString()
        binding.sugTvLunchDetails1.text=lunch.timeMinutes.toString()
    }
    private fun displayLunchDataSecondCard(lunch: Food){
        Glide
            .with(requireActivity().application.baseContext)
            .load(lunch.imageUrl)
            .into(binding.sugIvLunchImageFood2)
        binding.sugTvLunchNameFood2.text=lunch.recipeName.toString()
        binding.sugTvLunchDetails2.text=lunch.timeMinutes.toString()
    }

    private fun displayDinnerDataFirstCard(dinner: Food){
        Glide
            .with(requireActivity().application.baseContext)
            .load(dinner.imageUrl).
            into(binding.sugIvDinnerImageFood1)
        binding.sugTvDinnerNameFood1.text=dinner.recipeName.toString()
        binding.sugTvDinnerDetails1.text=dinner.timeMinutes.toString()
    }
    private fun displayDinnerDataSecondCard(dinner: Food){
        Glide
            .with(requireActivity().application.baseContext)
            .load(dinner.imageUrl)
            .into(binding.sugIvDinnerImageFood2)
        binding.sugTvDinnerNameFood2.text=dinner.recipeName.toString()
        binding.sugTvDinnerDetails2.text=dinner.timeMinutes.toString()
    }


}