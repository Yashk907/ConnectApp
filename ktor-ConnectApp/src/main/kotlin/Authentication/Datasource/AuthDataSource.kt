package com.example.Authentication.Datasource

import com.example.Models.User

interface AuthDataSource {

    suspend fun getUserByName(username : String) : User?

    suspend fun insertUser(user: User) : Boolean

}