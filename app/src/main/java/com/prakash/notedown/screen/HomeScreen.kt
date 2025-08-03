package com.prakash.notedown.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text ( text = "My Journaling")},
				actions = {
					IconButton(
						onClick = {}
					) {
						Icon(Icons.Default.Settings,contentDescription = "Settings")
					}
				}
			)
		}
	) { innerPadding ->
		Column (
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp)


		){
			OverViewCardRow()
			DailyLogList()
		}

	}
	
}

@Composable
fun OverViewCardRow() {
	Row(
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier.fillMaxWidth()
	){
		OverViewCard("Spend","$120", modifier = Modifier.weight(1f))
		OverViewCard("Calories","3000", modifier = Modifier.weight(1f))
		OverViewCard("Activities","9 hours", modifier = Modifier.weight(1f))
	}
}

@Composable
fun OverViewCard(title : String, value: String, modifier: Modifier = Modifier) {
	ElevatedCard (
		colors = CardDefaults.elevatedCardColors(),
		modifier = modifier
	){
		Column(
			modifier = Modifier.padding(12.dp)
		) {
			Text(title,style = MaterialTheme.typography.labelLarge)
			Text(value,style = MaterialTheme.typography.labelMedium)
		}
	}
}


@Composable
fun DailyLogList() {
	Column (
		verticalArrangement = Arrangement.spacedBy(8.dp)
	){
		Text("Today's Log",style = MaterialTheme.typography.titleMedium)
		Text("• ₹80 on groceries")
		Text("• 430 kcal breakfast")
		Text("• 1.5 hrs coding")
	}
}