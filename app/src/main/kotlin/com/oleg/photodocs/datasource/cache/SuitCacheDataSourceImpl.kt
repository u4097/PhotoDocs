package com.oleg.photodocs.datasource.cache

import com.oleg.photodocs.cache.AppCache
import com.oleg.photodocs.data.datasource.SuitCacheDataSource
import com.oleg.photodocs.datasource.model.SuitEntity
import com.oleg.photodocs.pref.PrefUtils.token
import kotlinx.coroutines.Deferred


class SuitCacheDataSourceImpl constructor(
    private val cache: AppCache<List<SuitEntity>>
) : SuitCacheDataSource {
    val key = "document"

    override fun set(documents: List<SuitEntity>): Deferred<List<SuitEntity>> {
        return cache.save(key, documents)
    }

    override fun get(): Deferred<List<SuitEntity>> =
        cache.load(key)
}
