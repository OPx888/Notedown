package com.prakash.notedown.data.local.repository

import com.prakash.notedown.data.local.dao.CaloriesDao
import com.prakash.notedown.data.local.entity.CaloriesEntry
import kotlinx.coroutines.flow.Flow

class CaloriesRepository( private val dao : CaloriesDao) {

	val allCalories : Flow<List<CaloriesEntry>> = dao.getAllCaloriesEntries()

	suspend fun insert(entry: CaloriesEntry){
		dao.insertCaloriesEntry(entry)
	}

	suspend fun delete(entry: CaloriesEntry){
		dao.deleteCaloriesEntry(entry)
	}

}