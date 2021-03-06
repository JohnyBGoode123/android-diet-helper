package com.example.diethelperapp.recipe

import com.example.diethelperapp.common.models.DishesModel

interface RecipeRepository {

    suspend fun loadRecipe(recipeId: Int): DishesModel
//
//    class Recipe(
//        val recipeId: String,
//        val ingredients: List<Ingredient>,
//        val calories: Double,
//        val protein: Double,
//        val fat: Double,
//        val carbohydrate: Double
//    )
//
//    class Ingredient(
//        val name: String,
//        val amount: Double,
//        val amountUnits: String
//    )
}