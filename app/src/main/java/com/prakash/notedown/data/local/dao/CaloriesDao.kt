package com.prakash.notedown.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prakash.notedown.data.local.entity.CaloriesEntry
import kotlinx.coroutines.flow.Flow



	@Dao
	interface CaloriesDao {
		@Insert(onConflict = OnConflictStrategy.REPLACE)
		suspend fun insertCaloriesEntry(entry: CaloriesEntry)

		@Query("SELECT * FROM calories_entries ORDER BY timestamp DESC")
		 fun getAllCaloriesEntries(): Flow<List<CaloriesEntry>>

		@Delete
		suspend fun deleteCaloriesEntry(entry: CaloriesEntry)
	}
