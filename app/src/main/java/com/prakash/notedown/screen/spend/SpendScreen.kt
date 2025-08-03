package com.prakash.notedown.screen.spend

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prakash.notedown.data.local.entity.SpendEntry
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Composable
fun SpendScreen() {
	val context = LocalContext.current
	val viewModel: SpendViewModel = viewModel(
		factory = SpendViewModelFactory(context.applicationContext as Application)
	)
	var amount by remember { mutableStateOf("") }
	var description by remember { mutableStateOf("")}

	val spendList by viewModel.todaySpend.collectAsState()


	Column (
		modifier = Modifier.padding(16.dp)
	){
		Text(
			text = "Add Spend",
			style = MaterialTheme.typography.titleLarge
		)

		OutlinedTextField(
			value = amount,
			onValueChange = {amount = it},
			label = {Text("Amount")},
			modifier = Modifier.fillMaxWidth()
		)

		OutlinedTextField(
			value = description,
			onValueChange = {description = it},
			label = {Text("Description")},
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(8.dp))

		Button(
			onClick = {
				val amountInt = amount.toIntOrNull()
				if (amountInt != null && description.isNotBlank()){
					val entry = SpendEntry(
						amount = amountInt,
						description = description
					)
					viewModel.addSpend(entry)
					amount = ""
					description = ""
				}
			},
			modifier = Modifier.align(Alignment.End)
		) {
			Text("Add")
		}

		Spacer(modifier = Modifier.height(16.dp))

		Text(
			text = "Spend History",
			style = MaterialTheme.typography.titleMedium
		)

		spendList.forEach {
			Text("• ₹${it.amount} on ${it.description}")
		}

	}


}

class SpendViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(SpendViewModel::class.java)) {
			@Suppress("UNCHECKED_CAST")
			return SpendViewModel(application) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}
