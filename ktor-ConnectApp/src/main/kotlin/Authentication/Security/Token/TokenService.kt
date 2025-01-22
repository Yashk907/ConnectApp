package com.example.Authentication.Security.Token

interface TokenService {
    fun generate(tokenConfig: TokenConfig,
                 vararg tokenClaim: TokenClaim) : String
}