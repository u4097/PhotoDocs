package com.oleg.photodocs.datasource.cache

import com.oleg.photodocs.cache.AppCache
import com.oleg.photodocs.data.datasource.DocumentCacheDataSource
import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.pref.PrefUtils.token
import kotlinx.coroutines.Deferred


class DocumentCacheDataSourceImpl constructor(
    private val cache: AppCache<List<DocumentEntity>>
) : DocumentCacheDataSource {
    val key = "document"

    override fun set(documents: List<DocumentEntity>): Deferred<List<DocumentEntity>> {
        return cache.save(key, documents)
    }

    override fun get(): Deferred<List<DocumentEntity>> =
        cache.load(key)
}
