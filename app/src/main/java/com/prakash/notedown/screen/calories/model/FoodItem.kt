package com.prakash.notedown.screen.calories.model

data class FoodItem(
	val name: String,
	val grams : Int,
	val calories: Int,
	val carbs: Int,
	val protein: Int,
	val fat: Int,
	val timestamp: Long = System.currentTimeMillis()

)
