package com.example.masalafoodapplication.ui.quick_recipes.adapters

import com.example.masalafoodapplication.data.domain.models.Food


interface QuickRecipesInteractionListener {
   fun onClickRecipesCard(food : Food){}
}