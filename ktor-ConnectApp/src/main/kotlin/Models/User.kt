package com.example.Models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import java.util.Date


data class User(
    val username : String,
    @BsonId val id : ObjectId =ObjectId(),
    val password : String,
    val profilePic : String,
    val salt : String ,
    val lastActive : Date
)
