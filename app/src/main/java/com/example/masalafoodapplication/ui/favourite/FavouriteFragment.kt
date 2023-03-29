package com.example.masalafoodapplication.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.airbnb.lottie.LottieDrawable
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentFavouriteBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.favourite.adapter.FavouriteAdapter
import com.example.masalafoodapplication.ui.favourite.adapter.FavouriteListener
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.util.Constants


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>(), FavouriteListener {
    private lateinit var adapter: FavouriteAdapter
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFavouriteBinding
        get() = FragmentFavouriteBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    fun setup() {
        val favouritesFood = dataManager.getAllFavouriteFood()
        if (favouritesFood.isEmpty()) {
            setupAnimation()
        } else {
            adapter = FavouriteAdapter(favouritesFood, this)
            binding.favRecycle.adapter = adapter
            binding.viewLottieLayer.visibility = View.INVISIBLE
            binding.textviewAddItemsHere.visibility = View.INVISIBLE
        }
    }

    override fun onClickHeart(position: Int) {
        dataManager.deleteFavourite(position)
        Toast.makeText(requireContext(), getString(R.string.deleted), Toast.LENGTH_SHORT).show()
        adapter.setData(dataManager.getAllFavouriteFood())
        if (dataManager.getAllFavouriteFood().isEmpty()) {
            setupAnimation()
        }
    }

    override fun onClickItem(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.FAV)
    }

    private fun setupAnimation() {
        binding.apply {
            binding.viewLottieLayer.visibility = View.VISIBLE
            viewLottieLayer.setAnimation(R.raw.heart)
            viewLottieLayer.repeatCount = LottieDrawable.INFINITE
            viewLottieLayer.playAnimation()
            binding.textviewAddItemsHere.visibility = View.VISIBLE
        }
    }

    private fun transitionToWithBackStackReplace(fragment: Fragment, tag: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }

    private fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }
}