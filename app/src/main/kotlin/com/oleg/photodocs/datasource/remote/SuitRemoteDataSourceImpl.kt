package com.oleg.photodocs.datasource.remote

import com.oleg.photodocs.data.datasource.SuitRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.SuitEntity
import com.oleg.photodocs.networking.SuitApi

class SuitRemoteDataSourceImpl constructor(
    private val api: SuitApi
) : SuitRemoteDataSource, BaseRepository() {

    override suspend fun get(): Resource<List<SuitEntity>>? {
        val response = safeApiCall(
            call = { api.getSuitAsync().await() },
            errorMessage = "Error Auth user"
        )
        return response
    }


}