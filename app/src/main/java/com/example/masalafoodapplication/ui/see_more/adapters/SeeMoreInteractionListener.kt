package com.example.masalafoodapplication.ui.see_more.adapters

import com.example.masalafoodapplication.data.domain.models.Food


interface SeeMoreInteractionListener {
   fun onClickRecipesCard(food : Food)
}