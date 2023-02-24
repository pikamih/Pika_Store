package com.example.pika_store.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pika_store.domain.Repo
import com.example.pika_store.domain.RepoImpl

class VMFactory(private val repo: RepoImpl):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }

}