package com.adiputrastwn.baseandroid.ui

import androidx.recyclerview.widget.RecyclerView
import com.adiputrastwn.baseandroid.databinding.ItemViewPokemonBinding
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.bumptech.glide.Glide

class PokemonViewHolder(private val binding: ItemViewPokemonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(pokemon: Pokemon) {
        binding.imageThumbnail.apply {
            Glide.with(context)
                .load(pokemon.getImageUrl())
                .into(this)
        }

    }
}