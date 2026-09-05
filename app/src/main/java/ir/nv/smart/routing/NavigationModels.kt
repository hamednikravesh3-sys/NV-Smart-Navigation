package ir.nv.smart.routing

data class GeoPoint(val lat: Double, val lon: Double)

data class RouteRequest(
    val origin: GeoPoint,
    val destination: GeoPoint,
    val alternatives: Int = 3,
    val avoidTolls: Boolean = false,
    val avoidUnpaved: Boolean = false
)

data class RouteAlternative(
    val id: String,
    val geometry: List<GeoPoint>,
    val distanceMeters: Long,
    val durationSeconds: Long,
    val trafficDelaySeconds: Long = 0,
    val score: Double = 0.0
)

data class Maneuver(
    val instructionFa: String,
    val distanceMeters: Int,
    val streetName: String? = null
)

interface RoutingEngine {
    suspend fun calculate(request: RouteRequest): List<RouteAlternative>
}
