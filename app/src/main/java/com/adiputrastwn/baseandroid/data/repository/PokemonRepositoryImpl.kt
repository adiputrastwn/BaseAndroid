package com.adiputrastwn.baseandroid.data.repository

import com.adiputrastwn.baseandroid.data.datasource.remote.PokemonRemoteDataSourceImpl
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.adiputrastwn.coreandroid.exception.Failure
import com.adiputrastwn.coreandroid.functional.Either
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteData: PokemonRemoteDataSourceImpl
) : PokemonRepository {

    override suspend fun getPokemonList(): Either<Failure, List<Pokemon>> {
        return pokemonRemoteData.getPokemonList()
    }
}