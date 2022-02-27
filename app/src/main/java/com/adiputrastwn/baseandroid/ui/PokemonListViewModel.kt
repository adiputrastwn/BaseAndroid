package com.adiputrastwn.baseandroid.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.adiputrastwn.baseandroid.data.datasource.paging.PokemonPagingSource
import com.adiputrastwn.baseandroid.data.datasource.remote.model.PokemonDetail
import com.adiputrastwn.baseandroid.data.repository.PokemonRepositoryImpl
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.adiputrastwn.coreandroid.functional.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepo: PokemonRepositoryImpl,
    private val pokemonPagingSource: PokemonPagingSource
) : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        pokemonPagingSource
    }.flow.cachedIn(viewModelScope)

    var pokemonList = MutableLiveData<List<Pokemon>?>()
    var pokemonDetail = MutableLiveData<PokemonDetail?>()

    fun getPokemonList() = viewModelScope.launch {
        when (val pokemonDataResponse = pokemonRepo.getPokemonList()) {
            is Either.Left -> print(pokemonDataResponse.a)
            is Either.Right -> {
                pokemonList.postValue(pokemonDataResponse.b)
            }
        }
    }

    fun getPokemonDetail(name: String) = viewModelScope.launch {
        when (val pokemonDetailResponse = pokemonRepo.getPokemonDetail(name)) {
            is Either.Left -> print(pokemonDetailResponse.a)
            is Either.Right -> {
                pokemonDetail.postValue(pokemonDetailResponse.b)
            }
        }
    }
}