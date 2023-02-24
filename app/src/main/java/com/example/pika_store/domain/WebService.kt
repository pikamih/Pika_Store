package com.example.pika_store.domain

import com.example.pika_store.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getTragosByName(@Query(value = "s") tragoName:String): DrinkList
}