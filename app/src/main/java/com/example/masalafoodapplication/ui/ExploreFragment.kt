package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentExploreBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.util.Constants
import com.example.masalafoodapplication.util.loadImage
import com.example.masalafoodapplication.data.domain.Food
import com.mindorks.editdrawabletext.DrawablePosition
import com.mindorks.editdrawabletext.onDrawableClickListener


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate
 private lateinit var adapter: SearchResultAdapter
    override fun setup() {
        adapter = SearchResultAdapter(emptyList())
        binding.recyclerSearchResult.adapter = adapter
        binding.searchBar.addTextChangedListener {
            val filter = DataManager.search(it.toString())
            adapter.setData(filter)


        }

        }

    override fun onClicks() {


    }

}









