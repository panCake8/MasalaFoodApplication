package com.example.masalafoodapplication.ui.random_recipes.adapters

import com.example.masalafoodapplication.data.domain.models.Food


interface RandomRecipesInteractionListener {
   fun onClickRecipesCard(food : Food)
}