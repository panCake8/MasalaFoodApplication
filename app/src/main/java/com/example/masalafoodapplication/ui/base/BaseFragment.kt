package com.example.masalafoodapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding
import com.example.masalafoodapplication.R
import com.example.masalafoodapplication.data.DataManager
import com.example.masalafoodapplication.util.Constants

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    lateinit var dataManager: DataManager

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataManager = (activity as HomeActivity).getDataManager()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
        onClicks()
    }

    abstract fun setup()
    open fun onClicks() {}

    fun transitionTo(fragment: Fragment) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            setReorderingAllowed(true)
        }
    }

    fun transitionToWithBackStackReplace(fragment: Fragment, tag: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }

    fun transitionToWithBackStackAdd(fragment: Fragment, fragment2: Fragment, tag: String) {
        parentFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
            setReorderingAllowed(true)
        }
    }

    fun onBack() {
        requireActivity().onBackPressed()
    }

    fun onBack(id: Int, tag: String) {
        newInstance(id, tag)
        requireActivity().onBackPressed()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun newInstanceToExplore(
        listKitchens: ArrayList<String>,
        listIngredient: ArrayList<String>,
        time: Float,
        key: String
    ) {
        val bundle = Bundle().apply {
            putStringArrayList(Constants.KITCHENS, listKitchens)
            putStringArrayList(Constants.INGREDIENT, listIngredient)
            putFloat(Constants.TIME_MINUTES, time)
        }
        parentFragmentManager.setFragmentResult(key, bundle)
    }

    fun newInstance(int: Int, key: String) {
        val bundle = Bundle()
        bundle.putInt(key, int)
        parentFragmentManager.setFragmentResult(key, bundle)
    }

    fun newInstance(string: String, key: String) {
        val bundle = Bundle()
        bundle.putString(key, string)
        parentFragmentManager.setFragmentResult(key, bundle)
    }

    fun newInstanceToSuggestion(list: ArrayList<String>, key: String) {
        val bundle = Bundle()
        bundle.putStringArrayList(Constants.SUGGESTION_FILTER, list)
        parentFragmentManager.setFragmentResult(key, bundle)
    }

}