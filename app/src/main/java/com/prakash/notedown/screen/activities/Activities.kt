package com.prakash.notedown.screen.activities

import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.prakash.notedown.data.local.entity.ActivityEntry
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun ActivitiesScreen(viewModel: ActivitiesViewModel = viewModel()) {
	val context = LocalContext.current
	val activities by viewModel.todayActivity.collectAsState()

	var label by remember { mutableStateOf("") }
    var startTime by remember { mutableStateOf<Long?>(null) }
	var endTime by remember { mutableStateOf<Long?>(null) }

	val timeFormatter = remember {
		SimpleDateFormat("hh:mm a", Locale.getDefault())
	}


	Column(modifier = Modifier.padding(16.dp)) {
		Text("Add Activity" , style = MaterialTheme.typography.titleLarge)


		OutlinedTextField(
			value = label,
			onValueChange = { label = it },
			label = { Text("Activity (e.g. Study)") },
			modifier = Modifier.fillMaxWidth()
		)

		Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			Button(onClick = {
				showTimePicker(context) { millis -> startTime = millis }
			}) {
				Text(startTime?.let { "Start: ${timeFormatter.format(Date(it))}" } ?: "Pick Start Time")
			}

			Button(onClick = {
				showTimePicker(context) { millis -> endTime = millis }
			}) {
				Text(endTime?.let { "End: ${timeFormatter.format(Date(it))}" } ?: "Pick End Time")
			}
		}


		Spacer(modifier = Modifier.height(8.dp))

		Button(
			onClick = {
				if (label.isNotBlank() && startTime != null && endTime != null){
					val entry = ActivityEntry(
						label = label,
						startTime = startTime!!,
						endTime = endTime!!
					)
					viewModel.addActivity(entry)
					label = ""
					startTime = null
					endTime = null

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
		activities.forEach {
			Text("• ${it.label}: ${timeFormatter.format(Date(it.startTime))} - ${timeFormatter.format(Date(it.endTime))}")
		}
//
//		Text(
//			"Today's Timeline",
//			style = MaterialTheme.typography.titleMedium
//		)
//		TimelineBar(logs)

	}


}

fun showTimePicker(context: Context, onTimeSelected: (Long) -> Unit) {
	val calendar = Calendar.getInstance()
	TimePickerDialog(
		context,
		{ _, hourOfDay, minute ->
			calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
			calendar.set(Calendar.MINUTE, minute)
			calendar.set(Calendar.SECOND, 0)
			calendar.set(Calendar.MILLISECOND, 0)
			onTimeSelected(calendar.timeInMillis)
		},
		calendar.get(Calendar.HOUR_OF_DAY),
		calendar.get(Calendar.MINUTE),
		false
	).show()
}

//
//@Composable
//fun TimelineBar(logs : List<ActivityLog>) {
//
//	val timelineWidth = 3000.dp
//
//	Box(
//		modifier = Modifier
//			.fillMaxWidth()
//			.height(60.dp)
//			.horizontalScroll(rememberScrollState())
//
//	){
//		Row(
//			modifier = Modifier
//				.width(timelineWidth)
//				.fillMaxHeight()
//		){
//			for (i in 0..23){
//				Box(
//					modifier = Modifier
//						.width(120.dp)
//						.fillMaxHeight()
//						.padding(end = 1.dp),
//					contentAlignment = Alignment.Center
//				){
//					Divider(modifier = Modifier.fillMaxHeight(), thickness = 1.dp)
//					Text("$i", style = MaterialTheme.typography.labelMedium)
//				}
//			}
//		}
//		logs.forEach{ log ->
//			val startTime = timeToFloat(log.startTime)
//			val endTime = timeToFloat(log.endTime)
//			val blockWidth = ((endTime - startTime) * 120).dp
//			val blockOffSet = (startTime * 120).dp
//
//			Box(
//				modifier = Modifier
//					.offset(x = blockOffSet)
//					.width(blockWidth)
//					.height(60.dp)
//					.padding(vertical = 8.dp)
//					.background(MaterialTheme.colorScheme.primary.copy(0.6f)),
//				contentAlignment = Alignment.CenterStart
//			){
//				Text(
//					log.label,
//					style = MaterialTheme.typography.labelSmall,
//					modifier = Modifier.padding(start = 8.dp)
//				)
//			}
//
//		}
//	}
//
//
//
//}
//
//fun timeToFloat(time : String) : Float{
//	val parts = time.split(":")
//	if (parts.size != 2 ) return 0f
//	val hours = parts[0].toIntOrNull() ?: 0
//	val minutes = parts[1].toIntOrNull() ?: 0
//	return hours + (minutes / 60f)
//
//}


