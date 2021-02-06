package com.example.nytcleanarcitecture.data.remote.base.interceptor

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ConnectivityInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {
    @SuppressLint("MissingPermission")
    fun Context.isNetworkConnected(): Boolean {
        val connectivityManager: ConnectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            with(connectivityManager) {
                val networkCapabilities = getNetworkCapabilities(activeNetwork)
                return networkCapabilities != null &&
                    (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN))
            }
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected &&
                (activeNetwork.type == ConnectivityManager.TYPE_WIFI || activeNetwork.type == ConnectivityManager.TYPE_MOBILE ||
                    activeNetwork.type == ConnectivityManager.TYPE_VPN)
        }
    }

    @Throws(IOException::class)
    override
    fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isNetworkConnected()) {
            throw IOException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}
