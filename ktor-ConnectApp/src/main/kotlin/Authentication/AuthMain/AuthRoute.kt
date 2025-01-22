package com.example.Authentication.AuthMain

import com.example.Authentication.Datasource.MongoAuthDatasource
import com.example.Authentication.Security.Hash.SHA256HashService
import io.ktor.server.routing.*

fun Route.SignUp(
    hashService: SHA256HashService,
    datasource: MongoAuthDatasource
){
    post("signUp") {

    }
}