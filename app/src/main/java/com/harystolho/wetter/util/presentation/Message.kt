package com.harystolho.wetter.util.presentation

import android.content.Context
import com.harystolho.wetter.R

interface Message {
    fun getMessage(context: Context): String
}

class UnknownError : Message {
    override fun getMessage(context: Context): String {
        return context.getString(R.string.error_message_unknown)
    }
}

class NoInternetError : Message {
    override fun getMessage(context: Context): String {
        return context.getString(R.string.error_message_no_internet)
    }
}