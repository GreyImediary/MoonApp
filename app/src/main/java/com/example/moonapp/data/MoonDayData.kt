package com.example.moonapp.data

import com.google.gson.annotations.SerializedName

data class MoonDayData(
    @SerializedName("location")
    val moonDayLocation: MoonDayLocation
)

data class MoonDayLocation(
    @SerializedName("currentConditions")
    val currentCondition: CurrentCondition
)

data class CurrentCondition(
    @SerializedName("moonphase")
    val moonPhase: Double
)
