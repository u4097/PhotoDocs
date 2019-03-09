package com.oleg.photodocs.datasource.model

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.SuitModel
import com.squareup.moshi.Json

data class SuitEntity(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "suit")
    val suit: String
)

fun SuitEntity.mapToDomain(): SuitModel = SuitModel(
    id = this.id,
    name = this.name,
    suit = this.suit
)


fun List<SuitEntity>.mapToDomain(): List<SuitModel> = map {
    it.mapToDomain()
}


fun Resource<List<SuitEntity>>.mapToDomain(): Resource<List<SuitModel>> = Resource<List<SuitModel>>(
    state = state,
    data = data?.mapToDomain(),
    message = message
)

