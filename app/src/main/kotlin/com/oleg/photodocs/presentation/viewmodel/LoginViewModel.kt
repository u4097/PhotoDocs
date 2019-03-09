package com.oleg.photodocs.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.usecases.LoginUseCase
import com.oleg.photodocs.datasource.model.LoginResponseEntity
import com.oleg.photodocs.presentation.model.login.Login
import com.oleg.photodocs.presentation.model.login.mapToDomain
import com.oleg.photodocs.presentation.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.*

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 22:22
 */


class LoginViewModel constructor(val loginUseCase: LoginUseCase) : AbstractViewModel() {

    val loginEventResponse = SingleLiveEvent<Resource<LoginResponseEntity>>()

    val tokenData = MutableLiveData<Resource<String>>()

    fun getToken() {
        scope.launch {
            val response = loginUseCase.getToken()
            tokenData.postValue(response)
        }
    }

    fun login(login: Login) {
        scope.launch {
            val response = loginUseCase.get(loginModel = login.mapToDomain())
            loginEventResponse.postValue(response)
        }
    }


}