package com.prakash.notedown.screen.spend

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items


@Composable
fun SpendScreen(viewModel: SpendViewModel = viewModel()) {
	var amount by remember { mutableStateOf("") }
	var description by remember { mutableStateOf("")}

	val spendList by viewModel.spendList.collectAsState()

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
				if (amount.isNotEmpty()){
					viewModel.addSpendEntry(amount.toInt(),description)
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

		LazyColumn {
			items(spendList){ item ->
				Text("${item.amount} - ${item.description}")
			}
		}

	}


}