package com.example.masalafoodapplication.ui.quick_recipes

import com.example.masalafoodapplication.data.domain.Food


interface QuickRecipesInteractionListener {
   fun onClickRecipesCard(food : Food){}
}