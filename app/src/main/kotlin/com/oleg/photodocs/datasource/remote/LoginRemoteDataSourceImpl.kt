package com.oleg.photodocs.datasource.remote

import com.oleg.photodocs.data.datasource.LoginRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.LoginEntity
import com.oleg.photodocs.networking.LoginApi
import com.oleg.photodocs.datasource.model.LoginResponseEntity

class LoginRemoteDataSourceImpl constructor(
    private val api: LoginApi
) : LoginRemoteDataSource, BaseRepository() {

    override suspend fun get(loginEntity: LoginEntity): Resource<LoginResponseEntity>? {
        val response = safeApiCall(
            call = { api.loginAsync(loginEntity).await() },
            errorMessage = "Error Auth user"
        )
        return response
    }

}