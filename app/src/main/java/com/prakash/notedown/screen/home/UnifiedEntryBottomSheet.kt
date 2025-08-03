//package com.prakash.notedown.screen.home
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.prakash.notedown.data.local.entity.CaloriesEntry
//
//@Composable
//fun UnifiedEntryBottomSheet(
//	onDismiss : () -> Unit,
//	onAdd : (
//		spend : Pair< Int , String>?,
//		calories : Pair< Int,String,>?,
//		activities : Triple<String, String, String>?
//
//			) -> Unit
//) {
//	var amount by remember { mutableStateOf("") }
//	var description by remember { mutableStateOf("") }
//	var foodName by remember { mutableStateOf("") }
//	var grams by remember { mutableStateOf("") }
//	var actLabel by remember { mutableStateOf("") }
//	var actStart by remember { mutableStateOf("") }
//	var actEnd by remember { mutableStateOf("") }
//
//	Surface (
//		tonalElevation = 4.dp,
//		shape = MaterialTheme.shapes.large,
//		modifier = Modifier.fillMaxWidth()
//	){
//		Column(modifier = Modifier.padding(16.dp)) {
//			Text("Entries", style = MaterialTheme.typography.titleLarge)
//
//			Text("Spend", style = MaterialTheme.typography.titleMedium)
//			OutlinedTextField(value = amount , onValueChange = {amount = it}, label = {Text("Amount")})
//			OutlinedTextField(value = description , onValueChange = {description = it}, label = {Text("Description")})
//
//			Spacer(Modifier.height(8.dp))
//
//			Text("Calorie Entry", style = MaterialTheme.typography.titleMedium)
//			OutlinedTextField(value = foodName, onValueChange = { foodName = it }, label = { Text("Food Name") })
//			OutlinedTextField(value = grams, onValueChange = { grams = it }, label = { Text("Grams of Food") })
//
//			Spacer(Modifier.height(8.dp))
//
//			Text("Activity Entry", style = MaterialTheme.typography.titleMedium)
//			OutlinedTextField(value = actLabel, onValueChange = { actLabel = it }, label = { Text("Activity Label") })
//			OutlinedTextField(value = actStart, onValueChange = { actStart = it }, label = { Text("Start Time (e.g. 09:00)") })
//			OutlinedTextField(value = actEnd, onValueChange = { actEnd = it }, label = { Text("End Time (e.g. 11:30)") })
//
//			Spacer(Modifier.height(12.dp))
//
//			Row(
//				horizontalArrangement = Arrangement.End,
//				modifier = Modifier.fillMaxWidth()
//			) {
//				TextButton(onClick = onDismiss) { Text("Cancel")}
//
//				Button(
//					onClick = {
//						val spendEntry = if (amount.isNotBlank() && description.isNotBlank())
//							amount.toIntOrNull()?.let{it to description}else null
//
//						val caloriesEntry = if (foodName.isNotBlank() && grams.isNotBlank()){
//							 amount.toIntOrNull()?.let{it to foodName}
//						}else null
//						val activityEntry = if (actLabel.isNotBlank() && actStart.isNotBlank() && actEnd.isNotBlank())
//							Triple(actLabel,actStart,actEnd) else null
//
//						onAdd(spendEntry,caloriesEntry,activityEntry)
//					}
//				) {
//					Text("Save")
//				}
//			}
//		}
//	}
//
//}