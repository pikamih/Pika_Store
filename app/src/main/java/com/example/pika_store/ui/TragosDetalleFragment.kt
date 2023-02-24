package com.example.pika_store.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.pika_store.AppDatabase
import com.example.pika_store.R
import com.example.pika_store.data.DataSource
import com.example.pika_store.data.model.Drink
import com.example.pika_store.data.model.DrinkEntity
import com.example.pika_store.domain.RepoImpl
import com.example.pika_store.ui.viewmodel.MainViewModel
import com.example.pika_store.ui.viewmodel.VMFactory
import kotlinx.android.synthetic.main.fragment_tragos_detalle.*

class TragosDetalleFragment : Fragment() {

    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSource(AppDatabase.getDatabase(requireActivity().applicationContext))
            )
        )
    }

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tragos_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext()).load(drink.imagen).centerCrop().into(img_trago)
        trago_title.text = drink.nombre
        tragos_desc.text = drink.descripcion
        if (drink.hasAlcoholic.equals("Non_Alcoholic")) {
            txt_has_alcohol.text = "Bebida sin Alcohol"
        } else {
            txt_has_alcohol.text = "Bebida con Alcohol"
        }

        btn_guardar_trago.setOnClickListener {
            viewModel.guardarTrago(
                DrinkEntity(
                    drink.tragoId,
                    drink.imagen,
                    drink.nombre,
                    drink.descripcion,
                    drink.hasAlcoholic
                )
            )
            Toast.makeText(requireContext(), "Se guardo el trago a favoritos", Toast.LENGTH_SHORT)
                .show()
        }
    }
}