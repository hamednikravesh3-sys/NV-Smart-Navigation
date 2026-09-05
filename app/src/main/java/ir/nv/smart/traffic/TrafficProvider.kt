package ir.nv.smart.traffic

import ir.nv.smart.routing.RouteAlternative

data class TrafficSummary(
    val level: Level,
    val delaySeconds: Long,
    val congestedMeters: Long
) {
    enum class Level { FREE, LIGHT, MODERATE, HEAVY }
}

interface TrafficProvider {
    suspend fun trafficFor(route: RouteAlternative): TrafficSummary
}
