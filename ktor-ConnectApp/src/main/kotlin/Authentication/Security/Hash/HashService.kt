package com.example.Authentication.Security.Hash

interface HashService {
 fun generateSaltedHash(value :String, SaltLength : Int =32) : SaltedHash?
 fun verify(value: String,saltedHash: SaltedHash) : Boolean
}