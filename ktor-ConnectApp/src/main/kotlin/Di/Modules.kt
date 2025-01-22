package com.example.Di

import com.example.Authentication.Datasource.AuthDataSource
import com.example.Authentication.Datasource.MongoAuthDatasource
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("Users")
    }
    single<MongoAuthDatasource> {
        MongoAuthDatasource(get())
    }
}