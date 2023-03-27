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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pika_store.AppDatabase
import com.example.pika_store.R
import com.example.pika_store.data.DataSource
import com.example.pika_store.data.model.Drink
import com.example.pika_store.domain.RepoImpl
import com.example.pika_store.ui.viewmodel.MainViewModel
import com.example.pika_store.ui.viewmodel.VMFactory
import com.example.pika_store.vo.Resource
import kotlinx.android.synthetic.main.fragment_favoritos.*

class FavoritosFragment : Fragment(), MainAdapater.OnTragoClickListener {

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
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers(){
        viewModel.getTragosFavoritos().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val lista = result.data.map {
                        Drink(it.tragoId, it.imagen, it.nombre, it.descripcion, it.hasAlcoholic)
                    }
                    rv_tragos_favoritos.adapter = MainAdapater(requireContext(), lista, this)
                }
                is Resource.Failure -> {

                }
            }
        })
    }

    private fun setupRecyclerView(){
        rv_tragos_favoritos.layoutManager = LinearLayoutManager(requireContext())
        rv_tragos_favoritos.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onTagoClick(drink: Drink, position: Int) {
        viewModel.deleteDrink(drink)
        rv_tragos_favoritos.adapter?.notifyItemRemoved(position)
        rv_tragos_favoritos.adapter?.notifyItemRangeRemoved(position, rv_tragos_favoritos.adapter?.itemCount!!)
    }
}