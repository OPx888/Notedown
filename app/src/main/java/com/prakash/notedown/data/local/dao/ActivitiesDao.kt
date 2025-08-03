package com.prakash.notedown.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prakash.notedown.data.local.entity.ActivityEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivitiesDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertActivityEntry(entry: ActivityEntry)

	@Query("SELECT * FROM activities_entries WHERE date = :date ORDER BY timestamp DESC")
	 fun getAllActivityEntries(date : String): Flow<List<ActivityEntry>>

	@Delete
	suspend fun deleteActivityEntry(entry: ActivityEntry)

}