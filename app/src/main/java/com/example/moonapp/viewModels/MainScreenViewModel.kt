package com.example.moonapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moonapp.api.MoonService
import com.example.moonapp.data.Result
import com.example.moonapp.getHttpCallBody
import okhttp3.*

class MainScreenViewModel(private val api: MoonService) : ViewModel() {

    /**
     * Возвращает объект LiveData, содержащий объект [Result] со значением лунного дня или
     * ошибкой.
     * */
    fun getMoonPhase() = liveData {
        emit(Result.Loading)
        try {
            emit(Result.Success(getMoonDay()))
        } catch (e : Exception) {
            emit(Result.Failure(e))
        }
    }

    /**
     * Возвращает числовое значение лунного дня, преобразовывая double с диапазоном 0.0-1.0
     * в Int с диапазоном 1-30
     * */

    /*
    * Изменил получение лунного дня с использования сервиса на парсинг астрологического сайта, на
    * котором правильно высчиывается день.
    * Данные фазы Луны из api либо имеют большой преиод обновления, либо нужен иной подход к
    * вычсилению лунного дня из фазы(при текущем он высичтывался неправильно)
    * */
    private suspend fun getMoonDay(): Int {
        /*val timeZoneId: String = TimeZone.getDefault().id
        val moonDayData: MoonDayData = api.getMoonPhase(timeZoneId)
        val moonPhase: Double = moonDayData.moonDayLocation.currentCondition.moonPhase*/


        val http = OkHttpClient()
        val request = Request.Builder()
            .url("https://mirkosmosa.ru/lunar-calendar")
            .build()

        val response = getHttpCallBody(http.newCall(request))



        /*return when {
            moonPhase - moonPhase.toInt() < 0.1 -> ceil(moonPhase).toInt()
            moonPhase - moonPhase.toInt() < 0.5 -> floor(moonPhase * 30).toInt()
            moonPhase - moonPhase.toInt() > 0.5 -> ceil(moonPhase * 30).toInt()
            moonPhase == 0.0 -> 1
            else -> (moonPhase * 30).toInt()

        }*/

        return parseMoonDay(response)
    }

    private fun parseMoonDay(htmlBody: String): Int {
        val classIndex = htmlBody.indexOf("moon-age_sym") // находим индекс класса тега, в котором находится день
        val sub = htmlBody.substring(classIndex) // отсекаем лишнее

        // вовзращаем значение лунного дня
        return sub.substring(
            sub.indexOf(">" ) + 1,
            sub.indexOf("<")
        ).toInt()
    }
}