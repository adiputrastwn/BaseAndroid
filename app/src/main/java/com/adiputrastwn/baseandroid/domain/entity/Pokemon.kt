package com.adiputrastwn.baseandroid.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String?,
    val url: String?
)