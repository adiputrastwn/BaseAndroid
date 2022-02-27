package com.adiputrastwn.baseandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.adiputrastwn.baseandroid.databinding.FragmentPokemonListBinding
import com.adiputrastwn.baseandroid.ui.PokemonListViewModel
import com.adiputrastwn.baseandroid.ui.PokemonPagingDataAdapter
import com.adiputrastwn.coreandroid.ui.recyclerview.GridSpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<PokemonListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pokemonPagingAdapter = PokemonPagingDataAdapter {
            it.name?.let { pokemonName ->
                val navDir =
                    PokemonListFragmentDirections.toPokemonDetailFragment(pokemonName)
                findNavController().navigate(navDir)
            }
        }
        binding.recyclerViewPokemon.apply {
            val spanCount = 2
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            adapter = pokemonPagingAdapter
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
            addItemDecoration(GridSpacesItemDecoration(spacingInPixels, spanCount, true))
        }
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.flow.collectLatest { pagingData ->
                pokemonPagingAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}