package com.adiputrastwn.baseandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adiputrastwn.baseandroid.data.datasource.paging.PokemonPagingSource
import com.adiputrastwn.baseandroid.data.datasource.remote.model.PokemonDetail
import com.adiputrastwn.baseandroid.data.repository.PokemonRepositoryImpl
import com.adiputrastwn.coreandroid.functional.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepositoryImpl,
    private val pokemonPagingSource: PokemonPagingSource
) : ViewModel() {

    var pokemonDetail = MutableLiveData<PokemonDetail?>()

    fun getPokemonDetail(name: String) = viewModelScope.launch {
        when (val pokemonDetailResponse = pokemonRepo.getPokemonDetail(name)) {
            is Either.Left -> print(pokemonDetailResponse.a)
            is Either.Right -> {
                pokemonDetail.postValue(pokemonDetailResponse.b)
            }
        }
    }
}