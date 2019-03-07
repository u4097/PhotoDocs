package com.oleg.photodocs.networking

import com.oleg.photodocs.datasource.model.login.LoginEntity
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 21:40
 */

interface LoginApi {
    @GET("auth/login/")
    fun loginAsync(
        @Body loginEntity: LoginEntity
    ): Deferred <Response<LoginResponse>>
}
