package com.example.masalafoodapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.Cuisine
import com.example.masalafoodapplication.databinding.ItemCuisineBinding
import com.example.masalafoodapplication.util.loadImage

class CuisinesAdapter(private val cuisines: List<Cuisine>) :
    RecyclerView.Adapter<CuisinesAdapter.CuisineViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuisineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cuisine, parent, false)
        return CuisineViewHolder(view)
    }

    override fun getItemCount() = cuisines.size

    override fun onBindViewHolder(holder: CuisineViewHolder, position: Int) {
        val currentCuisine = cuisines[position]
        holder.binding.apply {
            imageCuisine.loadImage(currentCuisine.imageUrl)
            labelCuisineName.text = currentCuisine.name
        }
    }

    class CuisineViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val binding = ItemCuisineBinding.bind(viewItem)
    }

}