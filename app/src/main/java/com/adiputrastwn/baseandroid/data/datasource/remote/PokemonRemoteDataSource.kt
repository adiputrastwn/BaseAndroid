package com.adiputrastwn.baseandroid.data.datasource.remote

import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.adiputrastwn.coreandroid.exception.Failure
import com.adiputrastwn.coreandroid.functional.Either

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(): Either<Failure, List<Pokemon>>
}