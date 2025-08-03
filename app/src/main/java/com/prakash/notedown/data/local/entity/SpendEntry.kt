package com.prakash.notedown.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale

@Entity("spend_entries")
data class SpendEntry(
	@PrimaryKey(autoGenerate = true) val id: Int = 0,
	val amount: Int,
	val description: String,
	val date: String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(System.currentTimeMillis()),
	val timestamp: Long = System.currentTimeMillis()
)
