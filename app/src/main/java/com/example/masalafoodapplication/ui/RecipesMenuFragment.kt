package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentRecipesMenuBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.Food

class RecipesMenuFragment : BaseFragment<FragmentRecipesMenuBinding>() {
    private lateinit var list: List<Food>
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRecipesMenuBinding
        get() = FragmentRecipesMenuBinding::inflate


    override fun setup() {
        list = DataManager.showMostQuickRecipes()
//        addImage()
//        addPrepareTime()
//        addRecipesName()
    }

//    override fun onClicks() {
//        binding.icArrowBack.setOnClickListener {
//            onBack()
//        }
//    }

    private fun setImages(url: String, imageView: ImageView) {
        imageView.loadImage(url)
    }

    override fun onClicks() {
        TODO("Not yet implemented")
    }

//    private fun addImage() {
//        binding.apply {
//            listOf(
//                image1, image2, image3, image4
//            ).forEachIndexed { index, imageView ->
//                setImages(list[index].imageUrl, imageView)
//            }
//        }
//    }
//
//    private fun addRecipesName() {
//        binding.apply {
//            listOf(
//                recipeName1, recipeName2, recipeName3, recipeName4
//            ).forEachIndexed { index, textView ->
//                setRecipesNames(list[index].recipeName, textView)
//            }
//        }
//    }
//
//    private fun setRecipesNames(name: String, textView: TextView) {
//        textView.text = name
//    }
//
//    @SuppressLint("SetTextI18n")
//    private fun addPrepareTime() {
//        binding.apply {
//            listOf(
//                , prepareTime2, prepareTime3, prepareTime4
//            ).forEachIndexed { index, textView ->
//                setPrepareTimes(list[index].timeMinutes, textView)
//            }
//        }
//    }

//    private fun setPrepareTimes(time: Int, textView: TextView) {
//        textView.setPreparationTime(time)
//    }

}