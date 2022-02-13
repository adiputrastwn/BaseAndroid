package com.adiputrastwn.baseandroid.data.datasource.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
    val id: Int?,
    val name: String?,
    val height: Int?,
    val weight: Int?
)