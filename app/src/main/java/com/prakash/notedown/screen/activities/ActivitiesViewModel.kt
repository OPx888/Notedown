package com.prakash.notedown.screen.activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.prakash.notedown.data.local.AppDatabase
import com.prakash.notedown.data.local.entity.ActivityEntry
import com.prakash.notedown.data.local.repository.ActivityRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ActivitiesViewModel(application: Application) : AndroidViewModel(application) {

	private val dao = AppDatabase.getDatabase(application).activitiesDao()
	private val repository = ActivityRepository(dao)

	private val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

	val todayActivity = repository.getDate(today).stateIn(
		viewModelScope,
		SharingStarted.WhileSubscribed(),
		emptyList()
	)

	fun addActivity(entry : ActivityEntry) = viewModelScope.launch {
		repository.insert(entry)
	}

	fun deleteActivity(entry: ActivityEntry) = viewModelScope.launch {
		repository.delete(entry)
	}
}