package com.adiputrastwn.baseandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiputrastwn.baseandroid.data.repository.PokemonRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepositoryImpl
) : ViewModel() {

    fun getPokemonList() = viewModelScope.launch {
        val pokemonData = pokemonRepo.getPokemonList()
        print(pokemonData)
        print("success")
    }
}