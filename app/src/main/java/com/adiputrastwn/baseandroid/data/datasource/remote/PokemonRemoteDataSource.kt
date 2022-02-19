package com.adiputrastwn.baseandroid.data.datasource.remote

import com.adiputrastwn.baseandroid.data.datasource.remote.model.PokemonDetail
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.adiputrastwn.coreandroid.exception.Failure
import com.adiputrastwn.coreandroid.functional.Either

interface PokemonRemoteDataSource {
    suspend fun getPokemonList(offset: Int, limit: Int): Either<Failure, List<Pokemon>>
    suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetail>
}