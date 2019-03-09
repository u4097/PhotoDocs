package com.oleg.photodocs.datasource.model

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.BackgroundModel
import com.squareup.moshi.Json

data class BackgroundEntity(
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "color_code")
    val colorCode: String
)

fun BackgroundEntity.mapToDomain(): BackgroundModel = BackgroundModel(
    id = this.id,
    name = this.name,
    colorCode = this.colorCode
)


fun List<BackgroundEntity>.mapToDomain(): List<BackgroundModel> = map {
    it.mapToDomain()
}


fun Resource<List<BackgroundEntity>>.mapToDomain(): Resource<List<BackgroundModel>> = Resource<List<BackgroundModel>>(
    state = state,
    data = data?.mapToDomain(),
    message = message
)

