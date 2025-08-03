package com.prakash.notedown.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prakash.notedown.data.local.entity.SpendEntry
import kotlinx.coroutines.flow.Flow


@Dao
interface SpendDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertSpendEntry(entry:SpendEntry)

	@Query("SELECT * FROM spend_entries WHERE date = :date ORDER BY timestamp DESC")
	 fun getAllSpendEntries(date : String): Flow<List<SpendEntry>>

	 @Delete
	 suspend fun deleteSpendEntry(entry: SpendEntry)
}




