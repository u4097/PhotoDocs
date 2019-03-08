package com.oleg.photodocs.presentation.model.login

import com.oleg.photodocs.domain.model.login.LoginModel

data class Login(
    val login: String?,
    val password: String?
)


fun Login.mapToDomain(): LoginModel = LoginModel(
    login = this.login,
    password = this.password
)