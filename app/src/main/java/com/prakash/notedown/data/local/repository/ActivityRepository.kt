package com.prakash.notedown.data.local.repository

import com.prakash.notedown.data.local.dao.ActivitiesDao
import com.prakash.notedown.data.local.entity.ActivityEntry
import kotlinx.coroutines.flow.Flow

class ActivityRepository( private val dao : ActivitiesDao) {

	fun getDate(date : String): Flow<List<ActivityEntry>>{
		return dao.getAllActivityEntries(date)
	}

	suspend fun insert(entry: ActivityEntry){
		dao.insertActivityEntry(entry)
	}

	suspend fun delete(entry: ActivityEntry){
		dao.deleteActivityEntry(entry)
	}
}