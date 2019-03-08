package com.oleg.photodocs.data.datasource

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.login.LoginEntity
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred


interface LoginCacheDataSource {

    fun get(): Deferred<String>

    fun set(token: String?): Deferred<String>

}

interface LoginRemoteDataSource {

    suspend fun get(loginModel: LoginEntity): Resource<LoginResponse>?

}
