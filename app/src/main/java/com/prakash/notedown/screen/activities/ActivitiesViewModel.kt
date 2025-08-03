package com.prakash.notedown.screen.activities

import androidx.lifecycle.ViewModel
import com.prakash.notedown.screen.activities.model.ActivityLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ActivitiesViewModel : ViewModel() {

	private val _activityLog = MutableStateFlow<List<ActivityLog>>(emptyList())
	val activityLog : StateFlow<List<ActivityLog>> = _activityLog

	fun addActivity(label : String, startTime : String, endTime : String){
		val newActivity = ActivityLog(label,startTime,endTime)
		_activityLog.value = _activityLog.value + newActivity
	}
}