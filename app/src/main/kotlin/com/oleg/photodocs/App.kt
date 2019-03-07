package com.oleg.photodocs

import android.app.Application
import au.com.gridstone.debugdrawer.LumberYard
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) return

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        LeakCanary.install(this)

        LumberYard.install(this)
        AppConfiguration.init(this)
    }


    companion object {
        lateinit var instance: App
    }

}