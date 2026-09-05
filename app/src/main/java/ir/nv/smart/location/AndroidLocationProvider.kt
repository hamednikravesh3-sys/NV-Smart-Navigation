package ir.nv.smart.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import ir.nv.smart.routing.GeoPoint
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AndroidLocationProvider(context: Context) : LocationProvider {
    private val client = LocationServices.getFusedLocationProviderClient(context.applicationContext)

    @SuppressLint("MissingPermission")
    override suspend fun currentLocation(): GeoPoint? = suspendCoroutine { continuation ->
        val token = CancellationTokenSource()
        client.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, token.token)
            .addOnSuccessListener { location ->
                continuation.resume(location?.let { GeoPoint(it.latitude, it.longitude) })
            }
            .addOnFailureListener {
                continuation.resume(null)
            }
    }
}
