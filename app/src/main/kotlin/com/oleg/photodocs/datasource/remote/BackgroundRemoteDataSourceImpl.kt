package com.oleg.photodocs.datasource.remote

import com.oleg.photodocs.data.datasource.BackgroundRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.BackgroundEntity
import com.oleg.photodocs.networking.BackgroundApi

class BackgroundRemoteDataSourceImpl constructor(
    private val api: BackgroundApi
) : BackgroundRemoteDataSource, BaseRepository() {

    override suspend fun get(): Resource<List<BackgroundEntity>>? {
        val response = safeApiCall(
            call = { api.getBackgroundAsync().await() },
            errorMessage = "Error Auth user"
        )
        return response
    }


}