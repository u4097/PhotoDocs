package com.oleg.photodocs.mockapi

import com.oleg.photodocs.datasource.model.BackgroundEntity
import com.oleg.photodocs.networking.BackgroundApi
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockBackgroundApi(mockRetrofit: MockRetrofit) : BackgroundApi {

    override fun getBackgroundAsync(): Deferred<Response<List<BackgroundEntity>>> {
        val response = mockBackgrounds
        return delegate.returningResponse(response).getBackgroundAsync()
    }

    private val delegate: BehaviorDelegate<BackgroundApi> =
        mockRetrofit.create(BackgroundApi::class.java)


    private val mockBackgrounds = listOf(
        BackgroundEntity(
            id = 1,
            name = "Белый",
            colorCode = "#FFFFFF"
        ),
        BackgroundEntity(
            id = 2,
            name = "Серый 1",
            colorCode = "#F0F0F0"
        ),
        BackgroundEntity(
            id = 3,
            name = "Телесный",
            colorCode = "#FFF0F2"
        ),
        BackgroundEntity(
            id = 4,
            name = "Бежевый",
            colorCode = "#FFE7D6"
        ),
        BackgroundEntity(
            id = 5,
            name = "Серый 2",
            colorCode = "#A8A8A8"
        )
    )

}
