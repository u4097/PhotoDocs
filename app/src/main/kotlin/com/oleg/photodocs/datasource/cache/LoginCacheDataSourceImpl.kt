package com.oleg.photodocs.datasource.cache

import androidx.lifecycle.LiveData
import com.oleg.photodocs.cache.LiveDataCache
import com.oleg.photodocs.data.datasource.LoginCacheDataSource
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred


class LoginCacheDataSourceImpl constructor(
    private val cache: LiveDataCache<LoginResponse>
) : LoginCacheDataSource {
    val key = "token"

    override fun get(): Deferred<LoginResponse> =
        cache.load(key)

    override fun set(loginResponse: LoginResponse?): LiveData<LoginResponse> =
        cache.save(key, loginResponse)

}
