package com.prakash.notedown.screen.spend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.prakash.notedown.data.local.AppDatabase
import com.prakash.notedown.data.local.entity.SpendEntry
import com.prakash.notedown.data.local.repository.SpendRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



class SpendViewModel (application: Application) : AndroidViewModel(application) {


	private val dao = AppDatabase.getDatabase(application).spendDao()
	private val repository = SpendRepository(dao)

	private val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

	val todaySpend = repository.getAllSpendEntries(today).stateIn(
		viewModelScope,
		SharingStarted.WhileSubscribed(),
		emptyList()
	)

	fun addSpend(entry: SpendEntry) = viewModelScope.launch {
		repository.insert(entry)
	}

	fun deleteSpend(entry: SpendEntry) = viewModelScope.launch {
		repository.delete(entry)

	}


}

