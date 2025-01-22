package com.example.Authentication.AuthMain

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token : String
)
