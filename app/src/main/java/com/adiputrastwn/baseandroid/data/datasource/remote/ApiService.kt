package com.adiputrastwn.baseandroid.data.datasource.remote

import com.adiputrastwn.baseandroid.data.datasource.remote.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonListResponse>
}