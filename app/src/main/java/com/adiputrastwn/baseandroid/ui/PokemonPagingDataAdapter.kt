package com.adiputrastwn.baseandroid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.adiputrastwn.baseandroid.R
import com.adiputrastwn.baseandroid.databinding.ItemViewPokemonBinding
import com.adiputrastwn.baseandroid.domain.entity.Pokemon

class PokemonPagingDataAdapter(private val onClick: (Pokemon) -> Unit) :
    PagingDataAdapter<Pokemon, PokemonViewHolder>(PokemonComparator) {

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        getItem(position)?.let { pokemon ->
            holder.bind(pokemon)
            holder.itemView.setOnClickListener {
                onClick.invoke(pokemon)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemBinding =
            ItemViewPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(itemBinding)
    }

    object PokemonComparator : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            // Id is unique.
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }
}