package com.prakash.notedown.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale

@Entity("activities_entries")
data class ActivityEntry(
	@PrimaryKey(autoGenerate = true) val id: Int = 0,
	val label: String,
	val startTime: Long,
	val endTime: Long,
	val date : String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(System.currentTimeMillis()),
	val timestamp: Long = System.currentTimeMillis()
)
