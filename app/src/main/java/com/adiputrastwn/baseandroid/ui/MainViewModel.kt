package com.adiputrastwn.baseandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiputrastwn.baseandroid.data.repository.PokemonRepositoryImpl
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.adiputrastwn.coreandroid.functional.Either
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepositoryImpl
) : ViewModel() {

    var pokemonList = MutableLiveData<List<Pokemon>>()

    fun getPokemonList() = viewModelScope.launch {
        when (val pokemonDataResponse = pokemonRepo.getPokemonList()) {
            is Either.Left -> print(pokemonDataResponse.a)
            is Either.Right -> {
                pokemonList.postValue(pokemonDataResponse.b ?: emptyList())
                pokemonDataResponse.b.forEach {
                    println(it.getImageUrl())
                }
            }
        }
    }

    fun getPokemonDetail(name: String) = viewModelScope.launch {
        val pokemonData = pokemonRepo.getPokemonDetail(name)
    }
}