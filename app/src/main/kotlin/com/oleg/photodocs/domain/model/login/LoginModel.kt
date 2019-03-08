package com.oleg.photodocs.domain.model.login

import com.oleg.photodocs.datasource.model.LoginEntity

data class LoginModel(
    val login: String? = "NoName",
    val password: String?
)


fun LoginModel.mapToDataSource(): LoginEntity =
    LoginEntity(
        login = this.login,
        password = this.password
    )