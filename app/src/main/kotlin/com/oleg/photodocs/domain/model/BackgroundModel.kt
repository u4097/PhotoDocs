package com.oleg.photodocs.domain.model

import com.oleg.photodocs.data.repository.resouces.Resource
import com.oleg.photodocs.datasource.model.BackgroundEntity
import com.oleg.photodocs.presentation.model.Background
import com.squareup.moshi.Json

data class BackgroundModel(
    val id: Int,
    val name: String,
    val colorCode: String
)

fun BackgroundModel.mapToDataSource(): BackgroundEntity = BackgroundEntity (
    id = this.id,
    name = this.name,
    colorCode = this.colorCode
)

fun BackgroundModel.mapToPresentation(): Background = Background (
    id = this.id,
    name = this.name,
    colorCode = this.colorCode
)

fun List<BackgroundModel>.mapToPresentation(): List<Background> = map {
    it.mapToPresentation()
}


fun Resource<List<BackgroundModel>>.mapToPresentation(): Resource<List<Background>> = Resource<List<Background>>(
    state = state,
    data = data?.mapToPresentation(),
    message = message
)

