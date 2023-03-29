package com.example.masalafoodapplication.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface BaseInteractionListener
abstract class BaseAdapter<T, VB : ViewBinding>(
    private var items: List<T>
) : RecyclerView.Adapter<BaseAdapter.ItemViewHolder<VB>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<VB> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = createBinding(inflater, parent, viewType)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder<VB>, position: Int) {
        val item = items[position]
        bind(holder.binding, item)
    }

    abstract fun createBinding(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): VB

    abstract fun bind(binding: VB, item: T)

    open fun setItems(newItems: List<T>) {
        val diffResult =
            DiffUtil.calculateDiff(BaseDiffUtil(items, newItems, ::areItemsSame, ::areContentSame))
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    open fun areItemsSame(oldItem: T, newItem: T): Boolean {
        return oldItem?.equals(newItem) == true
    }

    open fun areContentSame(oldPosition: T, newPosition: T) = true

    class ItemViewHolder<VB : ViewBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)

}