package com.example.masalafoodapplication.ui.see_more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.domain.models.Food
import com.example.masalafoodapplication.databinding.FragmentSeeMoreBinding
import com.example.masalafoodapplication.ui.base.BaseFragment
import com.example.masalafoodapplication.ui.food_detail.FoodDetailFragment
import com.example.masalafoodapplication.ui.home.HomeItemType
import com.example.masalafoodapplication.ui.see_more.adapters.SeeMoreAdapter
import com.example.masalafoodapplication.ui.see_more.adapters.SeeMoreInteractionListener
import com.example.masalafoodapplication.util.Constants


class SeeMoreFragment : BaseFragment<FragmentSeeMoreBinding>(),
    SeeMoreInteractionListener {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeeMoreBinding
        get() = FragmentSeeMoreBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    fun setup() {
        listenToFragmentResult()
    }

    fun onClicks() {
        binding.titleToolbar.setNavigationOnClickListener {
            onBack()
        }
    }

    override fun onClickRecipesCard(food: Food) {
        newInstance(food.id, Constants.KEY_FOOD_ID)
        transitionToWithBackStackReplace(FoodDetailFragment(), Constants.QUICK_RECIPES)
    }

    private fun onBack() {
        requireActivity().onBackPressed()
    }

    private fun transitionToWithBackStackReplace(fragment: Fragment, tag: String) {
        parentFragmentManager.commit {
            add(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }
    private fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }
    private fun listenToFragmentResult() {
        parentFragmentManager.setFragmentResultListener(
            Constants.KEY_SEE_MORE,
            this
        ) { _, result ->
            val type = result.getSerializable(Constants.KEY_SEE_MORE) as HomeItemType
            bindFragment(type)
        }
    }
    private fun bindData(list: List<Food>,stringId:Int){
        val adapter = SeeMoreAdapter(list, this)
        binding.recyclerSeeMore.adapter = adapter
        binding.titleToolbar.title = getString(stringId)
    }
    private fun bindFragment(type:HomeItemType){
        when(type){
            HomeItemType.QUICK_AND_EASY ->
                bindData(dataManager.getRandomQuickRecipes(),R.string.quick_recipes)
            HomeItemType.STEP_BY_STEP ->
                bindData(dataManager.getMostStepsRecipes(),R.string.step_by_step)
            HomeItemType.RAMADAN_2023 ->
                bindData(dataManager.getRecipesByCuisine(Constants.ARAB),R.string.ramadan_2023)
            HomeItemType.VEGETARIAN ->
                bindData(dataManager.getVegetarianRecipes(),R.string.vegetarian_recipes)
            else ->{}

        }
    }

}