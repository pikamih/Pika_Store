package com.example.pika_store.domain

import com.example.pika_store.data.DataSource
import com.example.pika_store.data.model.Drink
import com.example.pika_store.data.model.DrinkEntity
import com.example.pika_store.vo.Resource

class RepoImpl(private val dataSource: DataSource): Repo {
    override suspend fun getTragosList(tragoName: String): Resource<List<Drink>> {
        return dataSource.getTragosByName(tragoName)
    }

    override suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return dataSource.getTragosFavoritos()
    }

    override suspend fun insertTrago(trago: DrinkEntity) {
        dataSource.inserTragoIntoRoom(trago)
    }

    override suspend fun deleteDrink(drink: Drink) {
        dataSource.deleteDrink(drink)
    }
}