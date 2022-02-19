package com.adiputrastwn.baseandroid.ui

import androidx.recyclerview.widget.RecyclerView
import com.adiputrastwn.baseandroid.databinding.ItemViewPokemonBinding
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class PokemonViewHolder(private val binding: ItemViewPokemonBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(pokemon: Pokemon) {
        binding.imageThumbnail.apply {
            Glide.with(context)
                .load(pokemon.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this)
        }

    }
}