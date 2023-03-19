package com.example.masalafoodapplication.ui
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.masalafoodapplication.databinding.FragmentQuickRecipesBinding
class QuickRecipesFragment :BaseFragment<FragmentQuickRecipesBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentQuickRecipesBinding
        get() = FragmentQuickRecipesBinding::inflate
    override fun setup() {
    }

    override fun onClicks() {

    }
}