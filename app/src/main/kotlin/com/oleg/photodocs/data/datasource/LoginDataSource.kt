package com.oleg.photodocs.data.datasource

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.login.UserModel
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred


interface LoginCacheDataSource {

    fun get(): Deferred<LoginResponse>

    fun set(user: UserModel?): Deferred<UserModel>

}

interface LoginRemoteDataSource {

    suspend fun get(loginModel: UserModel): Resource<LoginResponse>?

}
