package com.prakash.notedown.screen.spend.model

import android.util.Log

data class SpendEntry (
	val amount : Int,
	val description : String,
	val timestamp : Long = System.currentTimeMillis()
)