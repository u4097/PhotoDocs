package com.oleg.photodocs.datasource.cache

import com.oleg.photodocs.cache.AppCache
import com.oleg.photodocs.data.datasource.LoginCacheDataSource
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred


class LoginCacheDataSourceImpl constructor(
    private val cache: AppCache<String>
) : LoginCacheDataSource {
    val key = "token"

    override fun get(): Deferred<String> =
        cache.load(key)

    override fun set(token: String?): Deferred<String> =
        cache.save(key, token)

}
