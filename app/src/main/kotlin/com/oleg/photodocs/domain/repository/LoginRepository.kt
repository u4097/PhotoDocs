package com.oleg.photodocs.domain.repository

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.datasource.model.LoginResponseEntity

interface LoginRepository {

    suspend fun get(loginModel: LoginModel): Resource<LoginResponseEntity>?

    suspend fun getToken(): Resource<String>?

}
