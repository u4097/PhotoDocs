package com.oleg.photodocs

import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.networking.DocumentApi
import com.oleg.photodocs.presentation.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockDocumentApi(mockRetrofit: MockRetrofit) : DocumentApi {

    override fun getDocumentAsync(): Deferred<Response<List<DocumentEntity>>> {
        val response = LoginResponse(token)
        return delegate.returningResponse(response).getDocumentAsync()
    }

    private val delegate: BehaviorDelegate<DocumentApi> =
        mockRetrofit.create(DocumentApi::class.java)


    private val token =
        "mock_token"

    private val mockDocuments = listOf(
        DocumentEntity(
            id = 4,
            name = "Водит. права",
            icon = "http://185.244.173.11/media/icon/id-card.png",
            photoOptions = "10x15"
        ),
        DocumentEntity(
            id = 3,
            name = "Виза",
            icon = "http://185.244.173.11/media/icon/visa.png",
            photoOptions = "10x15"
        ),
        DocumentEntity(
            id = 2,
            name = "Загран. Паспорт",
            icon = "http://185.244.173.11/media/icon/passport_1.png",
            photoOptions = "10x15"
        ),
        DocumentEntity(
            id = 1,
            name = "Паспорт",
            icon = "http://185.244.173.11/media/icon/passport.png",
            photoOptions = "10x15"
        )
    )

}
