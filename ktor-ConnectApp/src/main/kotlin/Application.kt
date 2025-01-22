package com.example

import com.example.Authentication.Datasource.AuthDataSource
import com.example.Authentication.Datasource.MongoAuthDatasource
import com.example.Authentication.Security.Hash.SHA256HashService
import com.example.Authentication.Security.Token.TokenConfig
import com.example.Authentication.Security.Token.tokenServiceImpl
import com.example.Di.mainModule
import io.ktor.server.application.*
import org.koin.ktor.ext.get
import org.koin.ktor.ext.getKoin
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(
        Koin
    ){
        modules(mainModule)
    }

    val tokenConfig = TokenConfig(
        audience = environment.config.property("jwt.audience").toString(),
        issuer = environment.config.property("jwt.domain").toString(),
        secret = "jwt-secret",
        ExpiresIn = 365L * 1000L * 60L * 60L
    )
    val tokenService = tokenServiceImpl()
    val hashingService = SHA256HashService()
    val authDatasource : MongoAuthDatasource= get()
    configureFrameworks()
    configureSerialization()
    configureSecurity(tokenConfig)
    configureRouting(datasource =authDatasource ,
        tokenConfig = tokenConfig,
        hashService = hashingService,
        tokenServiceImpl = tokenService)
}
