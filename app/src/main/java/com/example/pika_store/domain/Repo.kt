package com.example.pika_store.domain

import com.example.pika_store.data.model.Drink
import com.example.pika_store.data.model.DrinkEntity
import com.example.pika_store.vo.Resource

interface Repo {
    suspend fun getTragosList(tragoName: String): Resource<List<Drink>>
    suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>>
    suspend fun insertTrago(trago: DrinkEntity)
    suspend fun deleteDrink(drink: Drink)
}