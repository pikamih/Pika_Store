package com.example.pika_store.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pika_store.R
import com.example.pika_store.base.BaseViewHolder
import com.example.pika_store.data.model.Drink
import kotlinx.android.synthetic.main.tragos_rows.view.*

class MainAdapater(
    private val context: Context, private val tragosList: List<Drink>,
    private val itemClickLister: OnTragoClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnTragoClickListener {
        fun onTagoClick(drink: Drink, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.tragos_rows, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.imagen).centerCrop().into(itemView.img_tragos)
            itemView.txt_titulo.text = item.nombre
            itemView.txt_descripciom.text = item.descripcion
            itemView.setOnClickListener{itemClickLister.onTagoClick(item, position)}
        }
    }
}