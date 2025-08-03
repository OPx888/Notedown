package com.prakash.notedown.screen.calories

import androidx.lifecycle.ViewModel
import com.prakash.notedown.screen.calories.model.FoodItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CaloriesViewModel : ViewModel() {
	private val _foodLog = MutableStateFlow<List<FoodItem>>(emptyList())
	val foodLog : StateFlow<List<FoodItem>> = _foodLog

	private val foodDatabase = mapOf(
		"rice" to NutrientsPer100g(130, 2, 28, 0f),
		"dal" to NutrientsPer100g(116, 9, 20, 0.5f),
		"egg" to NutrientsPer100g(155, 13, 1, 11f),
		"banana" to NutrientsPer100g(89, 1, 23, 0.3f),
		"oats" to NutrientsPer100g(389, 17, 66, 7f)
	)

	fun addFood(name: String, grams: Int){
		val nutrients = foodDatabase[name.lowercase()]
		if (nutrients != null){
			val multiplier = grams/100f
			val foodItem = FoodItem(
				name = name,
				grams = grams,
				calories = (nutrients.calories * multiplier).toInt(),
				carbs = (nutrients.carbs * multiplier).toInt(),
				protein = (nutrients.protein * multiplier).toInt(),
				fat = (nutrients.fat * multiplier).toInt()
			)
			_foodLog.value = _foodLog.value + foodItem


		}
	}
}




// In the same file, above or below your ViewModel

data class NutrientsPer100g(
	val calories: Int,
	val protein: Int,
	val carbs: Int,
	val fat: Float
)
