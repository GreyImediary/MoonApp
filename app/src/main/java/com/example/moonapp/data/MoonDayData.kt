package com.example.moonapp.data

import com.google.gson.annotations.SerializedName

/**
 * Дата-класс, который содержит значение лунной фазы
 * @see https://www.visualcrossing.com/resources/documentation/weather-api/weather-api-json-result-structure/
 * */
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
