package me.aleksandarzekovic.joke_hilt.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetManager @Inject constructor(private var applicationContext: Context) {

    val isConnectedToInternet: Boolean?
        @RequiresApi(Build.VERSION_CODES.M)
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            val ni = conManager.getNetworkCapabilities(conManager.activeNetwork)
            return ni?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        }
}