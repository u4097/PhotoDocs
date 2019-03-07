package com.oleg.photodocs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.usecases.LoginUseCase
import com.oleg.photodocs.presentation.LoginResponse
import com.oleg.photodocs.presentation.model.login.Login
import com.oleg.photodocs.presentation.model.login.mapToDomain
import com.oleg.photodocs.presentation.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 22:22
 */


class LoginViewModel constructor(val loginUseCase: LoginUseCase) : ViewModel() {


    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    val loginResponse = SingleLiveEvent<Resource<LoginResponse>>()


    fun login(login: Login) {
        scope.launch {
            val response = loginUseCase.get(loginModel = login.mapToDomain())
            loginResponse.postValue(response)
        }
    }


    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

}