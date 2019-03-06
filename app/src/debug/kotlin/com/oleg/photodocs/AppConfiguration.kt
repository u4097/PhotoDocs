package com.oleg.photodocs

import android.app.Activity
import android.app.Application
import android.content.Context.POWER_SERVICE
import android.os.PowerManager
import android.os.PowerManager.FULL_WAKE_LOCK
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
import au.com.gridstone.debugdrawer.*

object AppConfiguration {
    private lateinit var app: Application

    fun init(app: Application) {
        this.app = app
    }


    fun getRootViewContainerFor(activity: Activity): ViewGroup {
        return DebugDrawer.with(activity)
//            .addSectionTitle("Network")
//            .addModule(RetrofitModule(debugRetrofitConfig))
//            .addSectionTitle("Logs")
//            .addModule(OkHttpLoggerModule(httpLogger))
            .addModule(TimberModule())
            .addModule(LeakCanaryModule())
            .addSectionTitle("Device information")
            .addModule(DeviceInfoModule())
            .addSectionTitle("Version information")
            .addModule(VersionInfoModule())
            .buildMainContainer()
    }

    /**
     * Show the activity over the lock-screen and wake up the device. If you launched the app manually
     * both of these conditions are already true. If you deployed from the IDE, however, this will
     * save you from hundreds of power button presses and pattern swiping per day!
     */
    fun riseAndShine(activity: Activity) {
        activity.window.addFlags(FLAG_SHOW_WHEN_LOCKED);
        val power: PowerManager =  activity.getSystemService(POWER_SERVICE) as PowerManager
        val lock : PowerManager.WakeLock = power.newWakeLock(FULL_WAKE_LOCK , "photoDocks:wakeup!")
        lock.acquire(5)
        lock.release()
    }

}