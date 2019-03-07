package com.oleg.photodocs

import com.oleg.photodocs.datasource.model.login.LoginEntity
import com.oleg.photodocs.networking.LoginApi
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockRemoteApi(mockRetrofit: MockRetrofit) : LoginApi {
    private val delegate: BehaviorDelegate<LoginApi> =
        mockRetrofit.create(LoginApi::class.java)

    override fun loginAsync(loginEntity: LoginEntity): Deferred<Response<LoginResponse>> {
        val response = LoginResponse(token)
        return delegate.returningResponse(response).loginAsync(loginEntity)
    }

    private val token =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJ1c2VybmFtZSI6IkFkbWluIiwiZXhwIjoxNTUyNDk1MDc3LCJsb2dpbiI6IkFkbWluIiwib3JpZ19pYXQiOjE1NTE4OTAyNzd9.ggmZGNUafKRpXmLT2K05176auY01qeA9hS3XUhrhKVg"

}
