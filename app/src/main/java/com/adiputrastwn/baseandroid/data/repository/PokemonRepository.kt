package com.adiputrastwn.baseandroid.data.repository

import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.adiputrastwn.coreandroid.exception.Failure
import com.adiputrastwn.coreandroid.functional.Either

interface PokemonRepository {
    suspend fun getPokemonList(): Either<Failure, List<Pokemon>>
}