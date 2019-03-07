package com.oleg.photodocs.datasource.model.login

import com.oleg.photodocs.domain.model.login.LoginModel
import com.squareup.moshi.Json

class LoginEntity(


    @field:Json(name = "name")
    val name: String?,

    @field:Json(name = "password")
    val password: String?

)

fun LoginEntity.mapToDomain(): LoginModel =
    LoginModel(
        name = this.name,
        password = this.password
    )
