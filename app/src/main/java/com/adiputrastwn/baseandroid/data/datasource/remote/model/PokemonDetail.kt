package com.adiputrastwn.baseandroid.data.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
    val id: Int?,
    val name: String?,
    val height: Int?,
    val weight: Int?,
    val abilities: List<Abilities>?,
    val moves: List<Moves>?
) {
    fun getImageUrl(): String? {
        id?.let {
            return "https://cdn.traction.one/pokedex/pokemon/$it.png"
        }
        return null
    }
}