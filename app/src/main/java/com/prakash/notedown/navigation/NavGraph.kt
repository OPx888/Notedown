package com.prakash.notedown.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prakash.notedown.screen.activities.ActivitiesScreen
import com.prakash.notedown.screen.calories.CaloriesScreen
import com.prakash.notedown.screen.home.HomeScreen
import com.prakash.notedown.screen.spend.SpendScreen
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun NavGraph(navController : NavHostController,showSheet: MutableState<Boolean>) {
	NavHost(navController, startDestination = BottomNavItem.Home.route){
		composable(BottomNavItem.Home.route){
			HomeScreen(
				viewModel(),
				viewModel(),
				viewModel(),
				showSheet = showSheet
			)
		}
		composable(route = BottomNavItem.Spend.route) {
			SpendScreen()
		}
		composable(route = BottomNavItem.Calories.route) {
			CaloriesScreen()
		}
		composable(route = BottomNavItem.Activities.route) {
			ActivitiesScreen()
		}
	}
}