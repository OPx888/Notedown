package com.prakash.notedown.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("calories_entries")
data class CaloriesEntry(
	@PrimaryKey(autoGenerate = true) val id: Int = 0 ,
	val foodName: String,
	val grams: Int,
	val calories: Int,
	val protein: Int,
	val carbs: Int,
	val fat: Int,
	val timestamp: Long = System.currentTimeMillis()

)
