package com.oleg.photodocs.datasource.cache

import com.oleg.photodocs.cache.AppCache
import com.oleg.photodocs.data.datasource.DocumentCacheDataSource
import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.pref.PrefUtils.token
import kotlinx.coroutines.Deferred


class DocumentCacheDataSourceImpl constructor(
    private val cache: AppCache<String>
) : DocumentCacheDataSource {
    val key = "document"

    override fun set(documents: List<DocumentEntity>): Deferred<String> {
        return cache.save(key, token)
    }

    override fun get(): Deferred<String> =
        cache.load(key)
}
