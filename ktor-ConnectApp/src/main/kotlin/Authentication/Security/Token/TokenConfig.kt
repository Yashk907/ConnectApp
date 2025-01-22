package com.example.Authentication.Security.Token

data class TokenConfig(
    val audience : String,
    val issuer : String,
    val secret : String,
    val ExpiresIn : Long
)
