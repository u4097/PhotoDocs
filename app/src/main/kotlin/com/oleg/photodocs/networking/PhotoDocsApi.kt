package com.oleg.photodocs.networking

import com.oleg.photodocs.datasource.model.*
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

// Логин
interface LoginApi {
    @POST("auth/login/")
    fun loginAsync(
        @Body loginEntity: LoginEntity
    ): Deferred <Response<LoginResponseEntity>>
}

// Шаблоны документов
interface DocumentApi {
     @GET("templates")
     fun getDocumentAsync(): Deferred<Response<List<DocumentEntity>>>
}

// Костюмы
interface SuitApi {
    @GET("suit_types")
    fun getSuitAsync(): Deferred<Response<List<SuitEntity>>>
}

interface BackgroundApi {
    @GET("backgrounds")
    fun getBackgroundAsync(): Deferred<Response<List<BackgroundEntity>>>
}

// Покупка
interface PurchaseApi {
    @GET("purchase")
    fun getPurchaseAsync(): Deferred<Response<List<PurchaseEntity>>>
}
