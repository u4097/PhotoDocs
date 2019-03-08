package com.oleg.photodocs.domain.usecases

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.domain.repository.LoginRepository
import com.oleg.photodocs.presentation.LoginResponse


class LoginUseCase constructor(private val loginRepository: LoginRepository) {

    suspend fun get(loginModel: LoginModel): Resource<LoginResponse>? =
        loginRepository.get(loginModel)

    suspend fun getToken(): Resource<String>? =
        loginRepository.getToken()
}
