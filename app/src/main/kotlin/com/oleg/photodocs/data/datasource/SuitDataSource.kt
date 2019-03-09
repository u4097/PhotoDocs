package com.oleg.photodocs.data.datasource

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.SuitEntity
import kotlinx.coroutines.Deferred


interface SuitCacheDataSource {

    fun get(): Deferred<List<SuitEntity>>

    fun set(suits: List<SuitEntity>): Deferred<List<SuitEntity>>

}

interface SuitRemoteDataSource {

    suspend fun get(): Resource<List<SuitEntity>>?

}
