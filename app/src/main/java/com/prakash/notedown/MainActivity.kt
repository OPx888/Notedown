package com.prakash.notedown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.prakash.notedown.navigation.NavGraph
import com.prakash.notedown.screen.home.BottomNavBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prakash.notedown.screen.activities.ActivitiesViewModel
import com.prakash.notedown.screen.calories.CaloriesViewModel
import com.prakash.notedown.screen.spend.SpendViewModel

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()

		setContent {
			val spendViewModel: SpendViewModel = viewModel()
			val caloriesViewModel: CaloriesViewModel = viewModel()
			val activitiesViewModel: ActivitiesViewModel = viewModel()

			val navController = rememberNavController()
			val currentBackStackEntry by navController.currentBackStackEntryAsState()
			val currentRoute = currentBackStackEntry?.destination?.route

			val showSheet = remember { mutableStateOf(false) }
			Scaffold (
				bottomBar = {

					if (!showSheet.value && currentRoute in listOf("home", "spend", "calories", "activities")) {
						BottomNavBar(navController)
						}
				}


			){ innerPadding ->
				Box(modifier = Modifier.padding(innerPadding)){
					NavGraph(navController, showSheet = showSheet)
				}
			}

		}
	}
}
