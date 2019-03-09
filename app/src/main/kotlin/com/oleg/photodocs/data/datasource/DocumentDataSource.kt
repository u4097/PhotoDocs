package com.oleg.photodocs.data.datasource

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.DocumentEntity
import kotlinx.coroutines.Deferred


interface DocumentCacheDataSource {

    fun get(): Deferred<List<DocumentEntity>>

    fun set(documents: List<DocumentEntity>): Deferred<List<DocumentEntity>>

}

interface DocumentRemoteDataSource {

    suspend fun get(): Resource<List<DocumentEntity>>?

}
