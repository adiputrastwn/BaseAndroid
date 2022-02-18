package com.adiputrastwn.baseandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.adiputrastwn.baseandroid.databinding.FragmentFirstBinding
import com.adiputrastwn.baseandroid.ui.MainViewModel
import com.adiputrastwn.baseandroid.ui.PokemonRecyclerViewAdapter
import com.adiputrastwn.coreandroid.ui.recyclerview.GridSpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonAdapter = PokemonRecyclerViewAdapter()
        binding.recyclerViewPokemon.apply {
            val spanCount = 2
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            adapter = pokemonAdapter
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
            addItemDecoration(GridSpacesItemDecoration(spacingInPixels, spanCount, true))
        }
        mainViewModel.pokemonList.observe(viewLifecycleOwner) {
            it?.forEach { pokemon ->
                println(pokemon.name)
            }
            pokemonAdapter.pokemonList = it ?: emptyList()
            pokemonAdapter.notifyDataSetChanged()

        }
        mainViewModel.getPokemonList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}