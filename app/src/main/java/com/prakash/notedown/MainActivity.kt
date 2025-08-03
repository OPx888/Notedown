package com.prakash.notedown

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.prakash.notedown.navigation.NavGraph
import com.prakash.notedown.screen.BottomNavBar
import com.prakash.notedown.ui.theme.NotedownTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			val navController = rememberNavController()
			Scaffold (
				bottomBar = { BottomNavBar(navController)},
				floatingActionButton = {
					FloatingActionButton(
						onClick = {}
					){
						Icon(Icons.Default.Add,contentDescription = "Add")
					}
				}
			){ innerPadding ->
				Box(modifier = Modifier.padding(innerPadding)){
					NavGraph(navController)
				}
			}

		}
	}
}
