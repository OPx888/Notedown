package com.prakash.notedown.screen.calories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CaloriesScreen(viewModel: CaloriesViewModel = viewModel () ) {
	var foodName by remember { mutableStateOf("") }
	var foodGrams by remember { mutableStateOf("") }

	val foodLog by viewModel.foodLog.collectAsState()

	Column(modifier = Modifier.padding(16.dp)) {
		Text(
			text = "Add Food",
			style = MaterialTheme.typography.titleLarge
		)

		OutlinedTextField(
			value = foodName,
			onValueChange = {foodName = it},
			label = {Text("Food Name")},
			modifier = Modifier.fillMaxWidth()
		)

		OutlinedTextField(
			value = foodGrams,
			onValueChange = {foodGrams = it},
			label = {Text("Grams")},
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(8.dp))

		Button(
			onClick = {
				if (foodName.isNotBlank() && foodGrams.isNotBlank()){
					viewModel.addFood(foodName,foodGrams.toInt())
					foodName = ""
					foodGrams = ""
				}
			}
		) {
			Text("Add")
		}

		Spacer(modifier = Modifier.height(16.dp))

		Text(
			text = "Food Log",
			style = MaterialTheme.typography.titleMedium
		)
		LazyColumn {
			items(foodLog){ item ->
				Text("${item.name} - ${item.grams}g")
			}
		}

	}



}