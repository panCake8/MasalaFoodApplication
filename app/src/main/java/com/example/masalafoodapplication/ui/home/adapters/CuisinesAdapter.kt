package com.example.masalafoodapplication.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.data.domain.models.Cuisine
import com.example.masalafoodapplication.databinding.ItemCuisineBinding
import com.example.masalafoodapplication.ui.base.BaseAdapter
import com.example.masalafoodapplication.ui.home.HomeInteractionListener
import com.example.masalafoodapplication.util.loadImage

class CuisinesAdapter(
    items: List<Cuisine>,
    private val listener: HomeInteractionListener
) : BaseAdapter<Cuisine, ItemCuisineBinding>(items) {

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ItemCuisineBinding {
        return ItemCuisineBinding.inflate(inflater, parent, false)
    }

    override fun bind(binding: ItemCuisineBinding, item: Cuisine) {
        binding.apply {
            imageCuisine.loadImage(item.imageUrl)
            textCuisineName.text = item.name
            textCuisineRecipesCount.text = "${item.recipesCount} recipes"
            root.setOnClickListener { listener.onCuisineClicked(item) }
        }
    }
}