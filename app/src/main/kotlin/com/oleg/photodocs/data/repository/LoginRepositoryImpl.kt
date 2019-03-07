package com.oleg.photodocs.data.repository

import com.oleg.photodocs.data.datasource.LoginCacheDataSource
import com.oleg.photodocs.data.datasource.LoginRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.domain.model.login.mapToDataSource
import com.oleg.photodocs.domain.repository.LoginRepository
import com.oleg.photodocs.presentation.LoginResponse

class LoginRepositoryImpl constructor(
    private val cacheDataSource: LoginCacheDataSource,
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun get(loginModel: LoginModel): Resource<LoginResponse>? =
        remoteDataSource.get(loginModel.mapToDataSource())
}

