package com.example.pika_store.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pika_store.AppDatabase
import com.example.pika_store.R
import com.example.pika_store.data.DataSource
import com.example.pika_store.domain.RepoImpl
import com.example.pika_store.ui.viewmodel.MainViewModel
import com.example.pika_store.ui.viewmodel.VMFactory
import com.example.pika_store.vo.Resource

class FavoritosFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSource(
                    AppDatabase.getDatabase(requireActivity().applicationContext)
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    Log.d("Lista Favoritos", "${result.data}")
                }
                is Resource.Failure -> {

                }
            }
        })
    }
}