package com.prakash.notedown.screen.home

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.prakash.notedown.navigation.BottomNavItem

@Composable
fun BottomNavBar(navController: NavHostController) {
	val items = listOf(
		BottomNavItem.Home,
		BottomNavItem.Spend,
		BottomNavItem.Calories,
		BottomNavItem.Activities
	)
	NavigationBar {
		val currentRout = navController.currentBackStackEntryAsState().value?.destination?.route
		items.forEach{item ->
			NavigationBarItem(
				icon = {Icon(item.icon, contentDescription = item.label)},
				label = {Text(item.label)},
				selected = currentRout == item.route,
				onClick = {
					if (currentRout != item.route){
						navController.navigate(item.route){
							popUpTo(navController.graph.startDestinationId) { saveState = true}
							launchSingleTop = true
							restoreState = true
						}
					}
				}
			)


		}

	}
	
}