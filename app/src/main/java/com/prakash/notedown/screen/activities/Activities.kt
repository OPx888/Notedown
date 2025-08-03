package com.prakash.notedown.screen.activities

import android.R.attr.x
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import com.prakash.notedown.screen.activities.model.ActivityLog
import java.util.EnumSet.of

@Composable
fun ActivitiesScreen(viewModel: ActivitiesViewModel = viewModel ()) {
	var label by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf("") }
	var endTime by remember { mutableStateOf("") }

	val logs by viewModel.activityLog.collectAsState()

	Column(modifier = Modifier.padding(16.dp)) {
		Text("Add Activity" , style = MaterialTheme.typography.titleLarge)


		OutlinedTextField(
			value = label,
			onValueChange = { label = it },
			label = { Text("Activity (e.g. Study)") },
			modifier = Modifier.fillMaxWidth()
		)

		OutlinedTextField(
			value = startTime,
			onValueChange = { startTime = it },
			label = { Text("Start Time (e.g. 09:00)") },
			modifier = Modifier.fillMaxWidth()
		)

		OutlinedTextField(
			value = endTime,
			onValueChange = { endTime = it },
			label = { Text("End Time (e.g. 11:30)") },
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(8.dp))

		Button(
			onClick = {
				if (label.isNotBlank() && startTime.isNotBlank() && endTime.isNotBlank()) {
					viewModel.addActivity(label, startTime, endTime)
					label = ""
					startTime = ""
					endTime = ""
				}
			},
			modifier = Modifier.align(Alignment.End)
		) {
			Text("Add")
		}

		Spacer(modifier = Modifier.height(16.dp))

		Text(
			text = "Activity Log",
			style = MaterialTheme.typography.titleMedium

		)

		LazyColumn {
			items(logs ){ item ->
				Text("${item.label} - ${item.startTime} - ${item.endTime}")
			}
		}

		Text(
			"Today's Timeline",
			style = MaterialTheme.typography.titleMedium
		)
		TimelineBar(logs)

	}


}

@Composable
fun TimelineBar(logs : List<ActivityLog>) {

	val timelineWidth = 3000.dp

	Box(
		modifier = Modifier
			.fillMaxWidth()
			.height(60.dp)
			.horizontalScroll(rememberScrollState())

	){
		Row(
			modifier = Modifier
				.width(timelineWidth)
				.fillMaxHeight()
		){
			for (i in 0..23){
				Box(
					modifier = Modifier
						.width(120.dp)
						.fillMaxHeight()
						.padding(end = 1.dp),
					contentAlignment = Alignment.Center
				){
					Divider(modifier = Modifier.fillMaxHeight(), thickness = 1.dp)
					Text("$i", style = MaterialTheme.typography.labelMedium)
				}
			}
		}
		logs.forEach{ log ->
			val startTime = timeToFloat(log.startTime)
			val endTime = timeToFloat(log.endTime)
			val blockWidth = ((endTime - startTime) * 120).dp
			val blockOffSet = (startTime * 120).dp

			Box(
				modifier = Modifier
					.offset(x = blockOffSet)
					.width(blockWidth)
					.height(60.dp)
					.padding(vertical = 8.dp)
					.background(MaterialTheme.colorScheme.primary.copy(0.6f)),
				contentAlignment = Alignment.CenterStart
			){
				Text(
					log.label,
					style = MaterialTheme.typography.labelSmall,
					modifier = Modifier.padding(start = 8.dp)
				)
			}

		}
	}



}

fun timeToFloat(time : String) : Float{
	val parts = time.split(":")
	if (parts.size != 2 ) return 0f
	val hours = parts[0].toIntOrNull() ?: 0
	val minutes = parts[1].toIntOrNull() ?: 0
	return hours + (minutes / 60f)

}


