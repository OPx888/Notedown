package com.prakash.notedown.data.local.repository

import com.prakash.notedown.data.local.dao.SpendDao
import com.prakash.notedown.data.local.entity.SpendEntry
import kotlinx.coroutines.flow.Flow


class SpendRepository(private val dao: SpendDao) {

	fun getAllSpendEntries(date: String): Flow<List<SpendEntry>> = dao.getAllSpendEntries(date)
	suspend fun insert(entry: SpendEntry) = dao.insertSpendEntry(entry)
	suspend fun delete(entry: SpendEntry) = dao.deleteSpendEntry(entry)

}

