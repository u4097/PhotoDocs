package com.oleg.photodocs.domain.model.login

import com.oleg.photodocs.datasource.model.login.LoginEntity

data class LoginModel(
    val name: String? = "NoName",
    val password: String?
)


fun LoginModel.mapToDataSource(): LoginEntity = LoginEntity(
    name = this.name,
    password = this.password
)