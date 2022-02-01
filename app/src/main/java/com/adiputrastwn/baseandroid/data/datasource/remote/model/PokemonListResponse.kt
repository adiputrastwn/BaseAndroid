package com.adiputrastwn.baseandroid.data.datasource.remote.model

import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int?,
    val results: List<Pokemon>?,
    val next: String?,
    val previous: String?
)