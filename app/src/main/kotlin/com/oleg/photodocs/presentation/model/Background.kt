package com.oleg.photodocs.presentation.model

import com.oleg.photodocs.domain.model.BackgroundModel


data class Background(
    val id: Int,
    val name: String,
    val colorCode: String
)

fun Background.mapToDomain(): BackgroundModel = BackgroundModel(
    id = this.id,
    name = this.name,
    colorCode = this.colorCode
)
