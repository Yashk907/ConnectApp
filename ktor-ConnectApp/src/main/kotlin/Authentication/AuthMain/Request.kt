package com.example.Authentication.AuthMain

import kotlinx.serialization.Serializable

@Serializable
data class Request(
    val username : String,
    val password : String
)
