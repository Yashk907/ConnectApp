package com.example.Authentication.AuthMain

import com.auth0.jwt.JWT
import com.example.Authentication.AuthMain.authenticate
import com.example.Authentication.Datasource.MongoAuthDatasource
import com.example.Authentication.Security.Hash.SHA256HashService
import com.example.Authentication.Security.Hash.SaltedHash
import com.example.Authentication.Security.Token.TokenClaim
import com.example.Authentication.Security.Token.TokenConfig
import com.example.Authentication.Security.Token.TokenService
import com.example.Authentication.Security.Token.tokenServiceImpl
import com.example.Models.User
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signUp(
    hashService: SHA256HashService,
    datasource: MongoAuthDatasource
){
    post("signUp") {
        val request=kotlin.runCatching {
            call.receiveNullable<Request>()
        }.getOrNull()?:run {
            call.respond(HttpStatusCode.BadRequest,"Request problem")
            return@post
        }

        val isFieldBlank =request.username.isBlank() || request.password.isBlank()
        val shortPass = request.password.length<7

        if(isFieldBlank || shortPass){
            call.respond(HttpStatusCode.Conflict,"field is blank or password is too short")
            return@post
        }

        val saltedhash = hashService.generateSaltedHash(request.password)

        val user=User(
            username = request.username,
            password =saltedhash!!.hash,
            salt = saltedhash.salt
        )

        val wasAcknowledge = datasource.insertUser(user)

        if(!wasAcknowledge){
            call.respond(HttpStatusCode.BadRequest,"User not inserted to database properly")
            return@post
        }

        call.respond(HttpStatusCode.OK)
    }



}


fun Route.signIn(
    hashService: SHA256HashService,
    datasource: MongoAuthDatasource,
    tokenService: tokenServiceImpl,
    tokenConfig: TokenConfig
){
    post("signIn") {
        val request=kotlin.runCatching {
            call.receiveNullable<Request>()
        }.getOrNull()?:run {
            call.respond(HttpStatusCode.BadRequest,"Request problem")
            return@post
        }

        val user = datasource.getUserByName(request.username)
        if(user==null){
            call.respond(HttpStatusCode.NotFound, "user not found")
        }

        val isValidPass=hashService.verify(
            value = request.password,
            saltedHash = SaltedHash(
                hash= user!!.password,
                salt = user.salt
            )
        )

        if(!isValidPass){
            call.respond(HttpStatusCode.BadRequest,"Password is incorrect")
            return@post
        }

        val token =tokenService.generate(tokenConfig,
            TokenClaim(
                "userId",
                user.id.toString()
            )
        )


        call.respond(HttpStatusCode.OK,AuthResponse(token))
    }
}

fun Route.authenticate(){
    authenticate { get ("authenticate"){
        call.respond(HttpStatusCode.OK,"You are Authenticated")
    }
    }
}

fun Route.secret(){
    authenticate {
        get ("secret"){
            val principal = call.principal<JWTPrincipal>()
            val userId=principal?.getClaim("userId",String::class)
            call.respond(HttpStatusCode.OK,"User id is : $userId")
        }
    }
}