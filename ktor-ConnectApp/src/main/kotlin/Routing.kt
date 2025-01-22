package com.example

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.Authentication.AuthMain.authenticate
import com.example.Authentication.AuthMain.secret
import com.example.Authentication.AuthMain.signIn
import com.example.Authentication.AuthMain.signUp
import com.example.Authentication.Datasource.MongoAuthDatasource
import com.example.Authentication.Security.Hash.SHA256HashService
import com.example.Authentication.Security.Token.TokenConfig
import com.example.Authentication.Security.Token.tokenServiceImpl
import io.ktor.serialization.gson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureRouting(hashService: SHA256HashService,
                                 tokenConfig: TokenConfig,
                                 tokenServiceImpl: tokenServiceImpl,
                                 datasource: MongoAuthDatasource) {
    routing {
        signUp(hashService = hashService,
            datasource =datasource )
        signIn(hashService =hashService ,
            datasource = datasource,
            tokenConfig = tokenConfig,
            tokenService = tokenServiceImpl)
        authenticate()
        secret()
    }
}
