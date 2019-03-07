package com.oleg.photodocs.data.datasource

import androidx.lifecycle.LiveData
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.login.LoginEntity
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred


interface LoginCacheDataSource {

    fun get(): Deferred<LoginResponse>

    fun set(user: LoginResponse?): LiveData<LoginResponse>

}

interface LoginRemoteDataSource {

    suspend fun get(loginModel: LoginEntity): Resource<LoginResponse>?

}
