package com.oleg.photodocs

import android.app.Application
import au.com.gridstone.debugdrawer.LumberYard
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (LeakCanary.isInAnalyzerProcess(this)) return

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        LeakCanary.install(this)

        LumberYard.install(this)
        AppConfiguration.init(this)

        // Unique initialization of Dependency Injection library to allow the use of application context
        startKoin { androidContext(this@App) }

    }


    companion object {
        lateinit var instance: App
    }

}