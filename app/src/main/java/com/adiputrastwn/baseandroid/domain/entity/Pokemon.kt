package com.adiputrastwn.baseandroid.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String?,
    val url: String?
) {
    fun getImageUrl(): String? {
        url?.let {
            val index = it.split("/".toRegex()).dropLast(1).last()
            return "https://cdn.traction.one/pokedex/pokemon/$index.png"
        }
        return null
    }
}