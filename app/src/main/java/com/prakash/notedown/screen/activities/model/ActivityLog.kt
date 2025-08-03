package com.prakash.notedown.screen.activities.model

data class ActivityLog(
	val label : String,
	val startTime : String,
	val endTime : String,
	val date : String = getTodayDate()
)

fun getTodayDate() : String {
	val formater = java.text.SimpleDateFormat("yy-mm-dd")
	return formater.format(java.util.Date())
}
