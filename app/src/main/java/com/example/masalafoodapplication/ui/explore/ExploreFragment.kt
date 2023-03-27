package com.example.masalafoodapplication.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.databinding.FragmentExploreBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.explore.adapters.ExploreAdapter


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate
 private lateinit var adapter: ExploreAdapter
    override fun setup() {
        adapter = ExploreAdapter(emptyList())
        binding.recyclerSearchResult.adapter = adapter
        binding.searchBar.addTextChangedListener {
            val filter = DataManager.search(it.toString())
            adapter.setData(filter)


        }

        }

    override fun onClicks() {


    }

}









