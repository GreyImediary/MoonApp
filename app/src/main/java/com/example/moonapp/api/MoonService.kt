package com.example.moonapp.api

import com.example.moonapp.data.MoonDayData
import retrofit2.http.GET
import retrofit2.http.Query

interface MoonService {
    @GET("forecast?aggregateHours=24&includeAstronomy=true&combinationMethod=aggregate&contentType=json&unitGroup=metric&locationMode=single&key=E1QNE6L2XN5LQ849XB8R15ZQ4")
    suspend fun getMoonPhase(@Query("locations") timeZone: String): MoonDayData
}