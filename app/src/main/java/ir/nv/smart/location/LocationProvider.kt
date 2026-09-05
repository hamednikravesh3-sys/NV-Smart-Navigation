package ir.nv.smart.location

import ir.nv.smart.routing.GeoPoint

interface LocationProvider {
    suspend fun currentLocation(): GeoPoint?
}
