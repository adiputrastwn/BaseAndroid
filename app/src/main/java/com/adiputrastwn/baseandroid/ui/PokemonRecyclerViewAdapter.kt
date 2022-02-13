package com.adiputrastwn.baseandroid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adiputrastwn.baseandroid.databinding.ItemViewPokemonBinding
import com.adiputrastwn.baseandroid.domain.entity.Pokemon

class PokemonRecyclerViewAdapter() : RecyclerView.Adapter<PokemonViewHolder>() {

    var pokemonList: List<Pokemon> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemBinding =
            ItemViewPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}