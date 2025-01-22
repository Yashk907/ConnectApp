package com.example.Authentication.Security.Token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class tokenServiceImpl : TokenService {
    override fun generate(tokenConfig: TokenConfig, vararg tokenClaim: TokenClaim): String {
        var token = JWT.create()
            .withAudience(tokenConfig.audience)
            .withIssuer(tokenConfig.issuer)
            .withExpiresAt(Date(System.currentTimeMillis()+tokenConfig.ExpiresIn) )
        tokenClaim.forEach {
            claim->
            token=token.withClaim(claim.name,claim.value)
        }
        return token.sign(Algorithm.HMAC256(tokenConfig.secret))
    }
}