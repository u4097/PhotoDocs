package com.oleg.photodocs.datasource.model

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.DocumentModel
import com.squareup.moshi.Json

data class DocumentEntity(
    @field:Json(name = "icon")
    val icon: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "photo_options")
    val photoOptions: String
)

fun DocumentEntity.mapToDomain(): DocumentModel = DocumentModel(
    icon = this.icon,
    id = this.id,
    name = this.name,
    photoOptions = this.photoOptions
)


fun List<DocumentEntity>.mapToDomain(): List<DocumentModel> = map {
    it.mapToDomain()
}


fun Resource<List<DocumentEntity>>.mapToDomain(): Resource<List<DocumentModel>> = Resource<List<DocumentModel>>(
    state = state,
    data = data?.mapToDomain(),
    message = message
)

