package com.oleg.photodocs.data.repository

import com.oleg.photodocs.data.datasource.LoginCacheDataSource
import com.oleg.photodocs.data.datasource.LoginRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.data.repository.resouces.ResourceState
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.domain.model.login.mapToDataSource
import com.oleg.photodocs.domain.repository.LoginRepository
import com.oleg.photodocs.pref.PrefUtils.token
import com.oleg.photodocs.presentation.LoginResponse

class LoginRepositoryImpl constructor(
    private val cacheDataSource: LoginCacheDataSource,
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    private var token: String? = null

    override suspend fun get(loginModel: LoginModel): Resource<LoginResponse>? {
        var response = cacheDataSource.get()
        response.await().let {
            token = it
        }
        token?.let {

            if (!token?.isEmpty()!!) {
                return Resource(ResourceState.SUCCESS, LoginResponse(token!!))
            } else {
                return remoteDataSource.get(loginModel.mapToDataSource())
            }
        }

        val loginResponse = remoteDataSource.get(loginModel.mapToDataSource())
        cacheDataSource.set(loginResponse?.data?.token)
        return loginResponse
    }

}

