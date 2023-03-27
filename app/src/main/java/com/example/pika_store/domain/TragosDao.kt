package com.example.pika_store.domain

import androidx.room.*
import com.example.pika_store.data.model.Drink
import com.example.pika_store.data.model.DrinkEntity

@Dao
interface TragosDao {

     @Query("SELECT * FROM tragosEntity")
     suspend fun getAllFavoriteDrinks():List<DrinkEntity>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertFavorite(trago:DrinkEntity)

     @Delete
     suspend fun deleteDrink(drink: DrinkEntity)
}