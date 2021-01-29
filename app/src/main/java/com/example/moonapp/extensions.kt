package com.example.moonapp

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

suspend fun getHttpCallBody(call: Call) = suspendCoroutine<String> {
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            throw e
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                it.resume(response.body()!!.string())
            } else {
                it.resume("")
            }
        }
    })
}