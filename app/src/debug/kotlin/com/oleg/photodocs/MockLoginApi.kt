package com.oleg.photodocs

import com.oleg.photodocs.datasource.model.LoginEntity
import com.oleg.photodocs.networking.LoginApi
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockLoginApi(mockRetrofit: MockRetrofit) : LoginApi {

    private val delegate: BehaviorDelegate<LoginApi> =
        mockRetrofit.create(LoginApi::class.java)

    override fun loginAsync(loginEntity: LoginEntity): Deferred<Response<LoginResponse>> {
        val response = LoginResponse(token)
        return delegate.returningResponse(response).loginAsync(loginEntity)
    }

    private val token =
        "mock_token"

}
