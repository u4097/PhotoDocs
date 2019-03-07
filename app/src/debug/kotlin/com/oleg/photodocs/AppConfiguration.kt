package com.oleg.photodocs

import android.app.Activity
import android.app.Application
import android.content.Context.POWER_SERVICE
import android.os.PowerManager
import android.os.PowerManager.*
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
import au.com.gridstone.debugdrawer.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oleg.photodocs.networking.HttpConfiguration
import com.oleg.photodocs.networking.HttpConfiguration.API_URL
import com.oleg.photodocs.networking.LoginApi
import com.oleg.photodocs.presentation.utils.debugdrawer.VersionInfoModule
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

object AppConfiguration {
    private lateinit var app: Application

    fun init(app: Application) {
        this.app = app
    }

    private val endpoints = listOf(
        Endpoint("Mock", "http://localhost/mock/", isMock = true),
        Endpoint("Production", API_URL, isMock = false)
    )


    private val networkBehavior = NetworkBehavior.create()
    private val httpLogger by lazy { HttpLogger(app) }
    private val debugRetrofitConfig by lazy { DebugRetrofitConfig(app, endpoints, networkBehavior) }


    val api: LoginApi by lazy { createApi() }


    private fun createApi(): LoginApi {
        val currentEndpoint: Endpoint = debugRetrofitConfig.currentEndpoint
        val httpClient = HttpConfiguration.client.newBuilder()
            .addInterceptor(httpLogger.interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(currentEndpoint.url)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        if (currentEndpoint.isMock) {
            val mockRetrofit = MockRetrofit.Builder(retrofit).networkBehavior(networkBehavior).build()
            return MockRemoteApi(mockRetrofit)
        }

        return retrofit.create<LoginApi>(LoginApi::class.java)
    }


    fun getRootViewContainerFor(activity: Activity): ViewGroup {
        return DebugDrawer.with(activity)
            .addSectionTitle("Network")
            .addModule(RetrofitModule(debugRetrofitConfig))
            .addSectionTitle("Logs")
            .addModule(OkHttpLoggerModule(httpLogger))
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
        val power: PowerManager = activity.getSystemService(POWER_SERVICE) as PowerManager
        val lock: PowerManager.WakeLock = power.newWakeLock(FULL_WAKE_LOCK
                or ACQUIRE_CAUSES_WAKEUP
                or ON_AFTER_RELEASE,
            "photoDocks:wakeup!")
        lock.acquire(5)
        lock.release()
    }

}