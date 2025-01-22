package com.example.Models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Date


//@Serializable
data class User(
    val username : String,
    @BsonId val id : ObjectId =ObjectId(),
    val password : String,
    val profilePic : String?=null,
    val salt : String ,
    val lastActive : Date?=null
)
