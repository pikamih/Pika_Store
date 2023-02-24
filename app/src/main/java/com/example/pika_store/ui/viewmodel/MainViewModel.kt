package com.example.pika_store.ui.viewmodel

import androidx.lifecycle.*
import com.example.pika_store.data.model.DrinkEntity
import com.example.pika_store.domain.Repo
import com.example.pika_store.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo):ViewModel(){

    private val tragosData = MutableLiveData<String>()

    fun setTrago(tragoName: String){
        tragosData.value = tragoName
    }

    init {
        setTrago("margarita")
    }

    val fetchTragosList = tragosData.distinctUntilChanged().switchMap {nombreTrago ->

        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getTragosList(nombreTrago))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    fun guardarTrago(trago: DrinkEntity) {
        viewModelScope.launch {
            repo.insertTrago(trago)
        }
    }

    fun getTragosFavoritos() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getTragosFavoritos())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}