package com.example.masalafoodapplication.ui

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.masalafoodapplication.data.domain.Food

class ExploreDiffUtil(val oldList: List<Food> , val newList: List<Food>):DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size



    override fun getNewListSize() = newList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition].recipeName == newList[newItemPosition].recipeName

    }


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}