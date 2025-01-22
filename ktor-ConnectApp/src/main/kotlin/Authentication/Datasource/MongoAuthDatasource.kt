package com.example.Authentication.Datasource

import com.example.Models.User
import org.bson.types.ObjectId
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class MongoAuthDatasource(
   private val db : CoroutineDatabase
) : AuthDataSource{
    val users = db.getCollection<User>()
    override suspend fun getUserByName(username : String): User? {
        return users.findOne(User::username eq username)

    }

    override suspend fun insertUser(user: User): Boolean {
      return users.insertOne(user).wasAcknowledged()
    }

}