package com.example.moonapp

import android.content.Context
import android.content.SharedPreferences
import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

val Context.prefs: SharedPreferences
    get() = getSharedPreferences("MOON_SHARED_PREFS", Context.MODE_PRIVATE)