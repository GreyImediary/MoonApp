package com.example.moonapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moonapp.api.MoonService
import com.example.moonapp.data.MoonDayData
import com.example.moonapp.data.Result
import java.lang.Exception
import java.util.*
import kotlin.math.ceil
import kotlin.math.floor

class MainScreenViewModel(private val api: MoonService) : ViewModel() {
    fun getMoonPhase() = liveData {
        emit(Result.Loading)
        try {
            emit(Result.Success(getMoonDay()))
        } catch (e : Exception) {
            emit(Result.Failure(e))
        }
    }

    private suspend fun getMoonDay(): Int {
        val timeZoneId: String = TimeZone.getDefault().id
        val moonDayData: MoonDayData = api.getMoonPhase(timeZoneId)
        val moonPhase: Double = moonDayData.moonDayLocation.currentCondition.moonPhase

        return when {
            moonPhase - moonPhase.toInt() < 0.1 -> ceil(moonPhase).toInt()
            moonPhase - moonPhase.toInt() < 0.5 -> floor(moonPhase * 30).toInt()
            moonPhase - moonPhase.toInt() > 0.5 -> ceil(moonPhase * 30).toInt()
            moonPhase == 0.0 -> 1
            else -> (moonPhase * 30).toInt()

        }
    }
}