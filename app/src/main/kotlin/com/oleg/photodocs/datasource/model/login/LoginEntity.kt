package com.oleg.photodocs.datasource.model.login

import com.oleg.photodocs.domain.model.login.LoginModel
import com.squareup.moshi.Json

class LoginEntity(


    @field:Json(name = "login")
    val login: String?,

    @field:Json(name = "password")
    val password: String?

)

fun LoginEntity.mapToDomain(): LoginModel =
    LoginModel(
        login = this.login,
        password = this.password
    )
