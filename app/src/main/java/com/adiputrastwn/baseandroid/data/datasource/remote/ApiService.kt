package com.adiputrastwn.baseandroid.data.datasource.remote

import com.adiputrastwn.baseandroid.data.datasource.remote.model.PokemonDetail
import com.adiputrastwn.baseandroid.data.datasource.remote.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon")
    suspend fun getPokemonList(): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): Response<PokemonDetail>
}