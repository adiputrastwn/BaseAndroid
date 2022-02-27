package com.adiputrastwn.baseandroid.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Abilities(
    val ability: Ability?,
    @SerialName("is_hidden")
    val isHidden: Boolean = false,
    val slot: Int?
)