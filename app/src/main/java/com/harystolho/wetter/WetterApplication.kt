package com.harystolho.wetter


import android.app.Application
import com.harystolho.wetter.di.wetterInject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WetterApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WetterApplication)
            modules(wetterInject)
        }
    }

}