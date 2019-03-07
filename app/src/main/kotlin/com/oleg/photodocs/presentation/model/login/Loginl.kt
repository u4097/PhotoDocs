package com.oleg.photodocs.presentation.model.login

import com.oleg.photodocs.domain.model.login.LoginModel

data class Login(
    val name: String?,
    val password: String?
)


fun Login.mapToDomain(): LoginModel = LoginModel(
    name = this.name,
    password = this.password
)