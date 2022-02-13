package com.adiputrastwn.baseandroid.data.datasource.remote

import com.adiputrastwn.baseandroid.data.datasource.remote.helper.safeNetworkCall
import com.adiputrastwn.baseandroid.data.datasource.remote.model.PokemonDetail
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.adiputrastwn.coreandroid.exception.Failure
import com.adiputrastwn.coreandroid.functional.Either
import com.adiputrastwn.coreandroid.platform.NetworkHandler
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val networkHandler: NetworkHandler
) : PokemonRemoteDataSource {

    override suspend fun getPokemonList(): Either<Failure, List<Pokemon>> {
        return if (networkHandler.isNetworkAvailable()) {
            when (val response = safeNetworkCall { apiService.getPokemonList() }) {
                is Either.Right -> {
                    val pokemonList = response.b.results ?: emptyList()
                    Either.Right(pokemonList)
                }
                is Either.Left -> {
                    response
                }
            }
        } else {
            Either.Left(Failure.NetworkConnection)
        }
    }

    override suspend fun getPokemonDetail(name: String): Either<Failure, PokemonDetail> {
        return if (networkHandler.isNetworkAvailable()) {
            return safeNetworkCall { apiService.getPokemonDetail(name) }
        } else {
            Either.Left(Failure.NetworkConnection)
        }
    }
}