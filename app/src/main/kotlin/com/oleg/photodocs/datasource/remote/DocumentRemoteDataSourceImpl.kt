package com.oleg.photodocs.datasource.remote

import com.oleg.photodocs.data.datasource.DocumentRemoteDataSource
import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.networking.DocumentApi

class DocumentRemoteDataSourceImpl constructor(
    private val api: DocumentApi
) : DocumentRemoteDataSource, BaseRepository() {

    override suspend fun get(): Resource<List<DocumentEntity>>? {
        val response = safeApiCall(
            call = { api.getDocumentAsync().await() },
            errorMessage = "Error Auth user"
        )
        return response
    }


}