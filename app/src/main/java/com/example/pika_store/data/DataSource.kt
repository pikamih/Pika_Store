package com.example.pika_store.data

import com.example.pika_store.AppDatabase
import com.example.pika_store.data.model.Drink
import com.example.pika_store.data.model.DrinkEntity
import com.example.pika_store.data.model.asFavoriteEntity
import com.example.pika_store.vo.Resource
import com.example.pika_store.vo.RetrofitClient

class DataSource(private val appDatabase: AppDatabase) {
    suspend fun getTragosByName(nombreTrago: String): Resource<List<Drink>>{
        return Resource.Success(RetrofitClient.webservice.getTragosByName(nombreTrago).drinkList)
    }

    suspend fun inserTragoIntoRoom(trago: DrinkEntity){
        appDatabase.tragoDao().insertFavorite(trago)
    }

    suspend fun getTragosFavoritos(): Resource<List<DrinkEntity>> {
        return Resource.Success(appDatabase.tragoDao().getAllFavoriteDrinks())
    }

    suspend fun deleteDrink(drink: Drink){
        appDatabase.tragoDao().deleteDrink(drink.asFavoriteEntity())
    }
}