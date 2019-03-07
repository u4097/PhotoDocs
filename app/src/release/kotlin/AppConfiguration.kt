/*package com.oleg.photodocs

import android.app.Activity
import android.app.Application
import android.view.ViewGroup
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oleg.photodocs.networking.HttpConfiguration
import com.oleg.photodocs.networking.HttpConfiguration.API_URL
import com.oleg.photodocs.networking.RemoteApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

*//**
 * Release configuration for the sample app.
 *//*
object AppConfiguration {

  val api: RemoteApi = Retrofit.Builder()
      .baseUrl(API_URL)
      .client(HttpConfiguration.client)
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()
      .create(RemoteApi::class.java)

  // Keep this method to match debug implementation.
  fun init(app: Application) {}

  fun getRootViewContainerFor(activity: Activity): ViewGroup {
    return activity.findViewById(android.R.id.content) as ViewGroup
  }
}*/
