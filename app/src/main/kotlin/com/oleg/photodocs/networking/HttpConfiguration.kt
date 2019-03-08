package com.oleg.photodocs.networking

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object HttpConfiguration {

  const val API_URL = "http://185.244.173.11/v0/"

  val client: OkHttpClient = OkHttpClient.Builder()
      .addInterceptor(AuthInterceptor)
      .build()

  private object AuthInterceptor : Interceptor {

    override fun intercept(chain: Chain): Response {
      val url: HttpUrl = chain.request().url().newBuilder()
          .build()

      val request: Request = chain.request().newBuilder()
          .url(url)
          .build()

      return chain.proceed(request)
    }
  }
}
