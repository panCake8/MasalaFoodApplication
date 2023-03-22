package com.example.masalafoodapplication.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentRandomRecipesBinding
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.util.setPreparationTime
import com.kiko.fillapp.data.domain.Food

class RandomRecipesFragment : BaseFragment<FragmentRandomRecipesBinding>() {
    private lateinit var list: List<Food>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomRecipesBinding
        get() = FragmentRandomRecipesBinding::inflate


    override fun setup() {
        list = DataManager.showJustForYou()
        addImage()
        addPrepareTime()
        addRecipesName()

    }

    override fun onClicks() {
        binding.icArrowBack.setOnClickListener {
       onBack()
        }
    }

    private fun setImages(url: String, imageView: ImageView) {
        imageView.loadImage(url)
    }


    private fun addImage() {
        binding.apply {
            listOf(
                image1, image2, image3, image4
            ).forEachIndexed { index, imageView ->
                setImages(list[index].imageUrl, imageView)
            }
        }

    }

    private fun addRecipesName() {
        binding.apply {
            listOf(
                recipeName1, recipeName2, recipeName3, recipeName4
            ).forEachIndexed { index, textView ->
                setRecipesNames(list[index].recipeName, textView)
            }
        }
    }

    private fun setRecipesNames(name: String, textView: TextView) {
        textView.text = name
    }

    @SuppressLint("SetTextI18n")
    private fun addPrepareTime() {
        binding.apply {
            listOf(
                prepareTime1, prepareTime2, prepareTime3, prepareTime4
            ).forEachIndexed { index, textView ->
                setPrepareTimes(list[index].timeMinutes, textView)
            }
        }
    }

    private fun setPrepareTimes(time: Int, textView: TextView) {
        textView.setPreparationTime(time)
    }

}