package com.oleg.photodocs.networking

import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.datasource.model.LoginEntity
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 21:40
 */

interface LoginApi {
    @POST("auth/login/")
    fun loginAsync(
        @Body loginEntity: LoginEntity
    ): Deferred <Response<LoginResponse>>
}

interface DocumentApi {
     @GET("templates")
     fun getDocumentAsync(): Deferred<Response<List<DocumentEntity>>>
}
