package com.example.Authentication.Security.Hash

data class SaltedHash(
    val hash : String,
    val salt : String
)
