package com.example.Models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Chat(
    val userId : ObjectId,
    @BsonId val chatId : ObjectId = ObjectId(),
    val chatType : String ,
    val participants : List<User>

)
