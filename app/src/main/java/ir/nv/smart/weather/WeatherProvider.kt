package ir.nv.smart.weather

import ir.nv.smart.routing.GeoPoint

data class WeatherNow(
    val temperatureC: Int,
    val conditionFa: String,
    val alertFa: String? = null
)

interface WeatherProvider {
    suspend fun current(point: GeoPoint): WeatherNow?
}
