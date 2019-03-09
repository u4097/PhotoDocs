package com.oleg.photodocs.mockapi

import com.oleg.photodocs.datasource.model.SuitEntity
import com.oleg.photodocs.networking.SuitApi
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockSuitApi(mockRetrofit: MockRetrofit) : SuitApi {

    override fun getSuitAsync(): Deferred<Response<List<SuitEntity>>> {
        val response = mockSuits
        return delegate.returningResponse(response).getSuitAsync()
    }

    private val delegate: BehaviorDelegate<SuitApi> =
        mockRetrofit.create(SuitApi::class.java)


    private val mockSuits = listOf(
        SuitEntity(
            id = 1,
            name = "Костюм 1",
            suit = "http://185.244.173.11/media/suitsData/suit1.png"
        ),
        SuitEntity(
            id = 3,
            name = "Костюм 3",
            suit = "http://185.244.173.11/media/suitsData/suit3.png"
        ),
        SuitEntity(
            id = 4,
            name = "Костюм 4",
            suit = "http://185.244.173.11/media/suitsData/suit4.png"
        ),
        SuitEntity(
            id = 2,
            name = "Костюм 2",
            suit = "http://185.244.173.11/media/suitsData/suit2-removebg.png"
        )
    )

}
