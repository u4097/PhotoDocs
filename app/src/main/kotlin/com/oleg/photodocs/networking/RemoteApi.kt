package com.oleg.photodocs.networking

import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface RemoteApi {
    @GET("auth/login/")
    fun loginAsync(): Deferred<LoginResponse>
}