package com.prakash.notedown.screen.calories

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.prakash.notedown.data.local.AppDatabase
import com.prakash.notedown.data.local.entity.CaloriesEntry
import com.prakash.notedown.data.local.repository.CaloriesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CaloriesViewModel(application: Application) : AndroidViewModel(application) {

	private val dao = AppDatabase.getDatabase(application).caloriesDao()
	private val repository = CaloriesRepository(dao)

	val allCalories = repository.allCalories.stateIn(
		viewModelScope,
		SharingStarted.WhileSubscribed(),
		emptyList()
	)

	fun addCalories(entry : CaloriesEntry) = viewModelScope.launch {
			repository.insert(entry)

	}

	fun deleteCalories(entry: CaloriesEntry) = viewModelScope.launch {
		repository.delete(entry)
	}


}



