
package com.example.masalafoodapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentHistoryBinding


class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHistoryBinding
        get() = FragmentHistoryBinding::inflate


    override fun setup() {
    }

    override fun onClicks() {
        binding.arrowBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

    }

}