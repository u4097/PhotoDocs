package com.oleg.photodocs.data.repository

import com.oleg.photodocs.data.datasource.LoginCacheDataSource
import com.oleg.photodocs.data.datasource.LoginRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.data.repository.resouces.ResourceState
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.domain.model.login.mapToDataSource
import com.oleg.photodocs.domain.repository.LoginRepository
import com.oleg.photodocs.presentation.LoginResponse

class LoginRepositoryImpl constructor(
    private val cacheDataSource: LoginCacheDataSource,
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun getToken(): Resource<String>? {
        var token: String?
        var response = cacheDataSource.get()
        response.await().let {
            token = it
        }
        token?.let {
            if (!token?.isEmpty()!!) {
                return Resource(ResourceState.SUCCESS, token)
            } else {
                return Resource(ResourceState.ERROR, null, "Token is empty")
            }
        }
        return Resource(ResourceState.SUCCESS, token)
    }


        override suspend fun get(loginModel: LoginModel): Resource<LoginResponse>? {
            val loginResponse = remoteDataSource.get(loginModel.mapToDataSource())
            cacheDataSource.set(loginResponse?.data?.token)
            return loginResponse
        }

    }

