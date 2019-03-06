package com.oleg.photodocs

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RemoteApi {
    @GET("auth/login/")
    fun loginAsync(): Deferred<LoginResponse>
}