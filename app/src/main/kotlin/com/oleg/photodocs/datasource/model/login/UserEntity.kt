package com.oleg.photodocs.datasource.model.login

import com.oleg.photodocs.domain.login.UserModel
import com.squareup.moshi.Json

class UserEntity(


    @field:Json(name = "name")
    val name: String?,

    @field:Json(name = "password")
    val password: String?

)

fun UserEntity.mapToDomain(): UserModel = UserModel(
    name = this.name,
    password = this.password
)
