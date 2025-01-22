package com.example.Models

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import kotlin.jvm.internal.Ref.ObjectRef

enum class MessageStatus{
    READ,
    SEEN,
    DELIVERED
}
data class Message(
    @BsonId val messageId : ObjectId = ObjectId(),
     val chatid : ObjectId,
     val senderId : ObjectId,
     val userId : ObjectId,
    val content : String,
    val timeStamp : Long,
    val messageStatus: MessageStatus,

)