package com.prakash.notedown.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prakash.notedown.data.local.dao.ActivitiesDao
import com.prakash.notedown.data.local.dao.CaloriesDao
import com.prakash.notedown.data.local.dao.SpendDao
import com.prakash.notedown.data.local.entity.ActivityEntry
import com.prakash.notedown.data.local.entity.CaloriesEntry
import com.prakash.notedown.data.local.entity.SpendEntry
import java.time.Instant

@Database(
	entities = [
		SpendEntry::class,
		CaloriesEntry::class,
		ActivityEntry::class
	],
	version = 1,
	exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun spendDao(): SpendDao
	abstract fun caloriesDao(): CaloriesDao
	abstract fun activitiesDao(): ActivitiesDao

	companion object{
		@Volatile private var INSTANCE : AppDatabase? = null

		fun getDatabase(context: Context): AppDatabase{
			return INSTANCE ?: synchronized(this){
				Room.databaseBuilder(
					context.applicationContext,
					AppDatabase::class.java,
					"journaling_db"
				).build().also {
					INSTANCE = it
				}
			}
		}
	}
}



