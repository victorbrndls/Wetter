package com.harystolho.wetter.core.service

interface NetworkService {

    /**
     * @return [true] if the device has internet connection, that means it can send/receive data
     */
    fun hasInternetAccess(): Boolean

}