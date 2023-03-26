package com.example.masalafoodapplication.ui.food_detail.adapters



import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.databinding.ItemFoodDetailBinding

class FoodDetailAdapter(val list: List<String>):
    RecyclerView.Adapter<FoodDetailAdapter.FoodDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food_detail, parent, false)
        return FoodDetailViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FoodDetailViewHolder, position: Int) {
        val currentReciepy = list[position]
        holder.binding.apply {
            stepNumberTextview.text = "${position + 1}"
            stepIngredientTextview.text = currentReciepy
        }
    }

    override fun getItemCount() = list.size

    class FoodDetailViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem) {
        val binding = ItemFoodDetailBinding.bind(viewItem)
    }
}
