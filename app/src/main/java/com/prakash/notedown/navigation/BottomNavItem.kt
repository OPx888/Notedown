package com.prakash.notedown.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Fastfood

sealed class BottomNavItem( val route: String,val label: String, val icon: ImageVector){
	object Home : BottomNavItem("home","Home", Icons.Default.Home)
	object Spend : BottomNavItem("spend","Spend",Icons.Default.AttachMoney )
	object Calories : BottomNavItem("calories","Calories",Icons.Default.Fastfood)
	object Activities : BottomNavItem("activities","Activities",Icons.Default.CalendarMonth)
}