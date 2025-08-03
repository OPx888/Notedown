package com.prakash.notedown.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.prakash.notedown.screen.activities.ActivitiesScreen
import com.prakash.notedown.screen.calories.CaloriesScreen
import com.prakash.notedown.screen.home.HomeScreen
import com.prakash.notedown.screen.spend.SpendScreen

@Composable
fun NavGraph(navController : NavHostController ) {
	NavHost(navController, startDestination = BottomNavItem.Home.route){
		composable(route = BottomNavItem.Home.route){
			HomeScreen()
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