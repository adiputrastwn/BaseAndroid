package com.adiputrastwn.baseandroid.data.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.adiputrastwn.baseandroid.data.datasource.remote.ApiService
import com.adiputrastwn.baseandroid.domain.entity.Pokemon
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val apiService: ApiService
) : PagingSource<Int, Pokemon>() {

    companion object {
        const val POKEMON_STARTING_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: POKEMON_STARTING_PAGE_INDEX
        val offset = params.loadSize * page
        return try {
            val response = apiService.getPokemonList(offset, params.loadSize)
            val data = response.body()?.results
            var nextPageNumber: Int? = null
            if (!data.isNullOrEmpty()) {
                nextPageNumber = page + 1
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
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