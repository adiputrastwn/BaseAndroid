package com.adiputrastwn.baseandroid.data.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adiputrastwn.baseandroid.data.repository.PokemonRepositoryImpl
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import com.adiputrastwn.coreandroid.functional.getOrElse
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val pokemonRepo: PokemonRepositoryImpl
) : PagingSource<Int, Pokemon>() {

    companion object {
        const val POKEMON_STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: POKEMON_STARTING_PAGE_INDEX
        val offset = params.loadSize * (page - 1)
        return try {
            val data: MutableList<Pokemon> = mutableListOf()
            var nextPageNumber: Int? = null
            val response = pokemonRepo.getPokemonList(offset, params.loadSize)
            data.addAll(response.getOrElse(emptyList()))
            if (data.isNotEmpty()) {
                nextPageNumber = page + 1
            }
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}