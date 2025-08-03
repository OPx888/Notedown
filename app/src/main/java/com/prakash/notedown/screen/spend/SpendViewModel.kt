package com.prakash.notedown.screen.spend

import androidx.lifecycle.ViewModel
import com.prakash.notedown.screen.spend.model.SpendEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SpendViewModel : ViewModel(){

	private val _spendList = MutableStateFlow<List<SpendEntry>>(emptyList())
	val spendList : StateFlow<List<SpendEntry>> = _spendList

	fun addSpendEntry(amount : Int, description : String){
		val newEntry = SpendEntry(amount,description)
		_spendList.value = _spendList.value + newEntry

	}
}