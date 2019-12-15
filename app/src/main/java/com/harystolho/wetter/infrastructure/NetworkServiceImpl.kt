package com.harystolho.wetter.infrastructure

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.harystolho.wetter.core.service.NetworkService

class NetworkServiceImpl(private val context: Context) : NetworkService {

    override fun hasInternetAccess(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = cm.activeNetwork ?: return false
        val networkCapabilities = cm.getNetworkCapabilities(network) ?: return false

        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}