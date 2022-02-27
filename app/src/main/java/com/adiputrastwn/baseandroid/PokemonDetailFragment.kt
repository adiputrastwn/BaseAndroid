package com.adiputrastwn.baseandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.adiputrastwn.baseandroid.data.datasource.remote.model.PokemonDetail
import com.adiputrastwn.baseandroid.databinding.FragmentPokemonDetailBinding
import com.adiputrastwn.baseandroid.ui.PokemonDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {

    private val args: PokemonDetailFragmentArgs by navArgs()
    private var _binding: FragmentPokemonDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<PokemonDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemonDetail.observe(viewLifecycleOwner) { pokemonDetail ->
            pokemonDetail?.let { it ->
                showPokemonDetail(it)
            }
        }
        viewModel.getPokemonDetail(args.pokemonName)
    }

    private fun showPokemonDetail(pokemonDetail: PokemonDetail) {
        binding.textviewSecond.text = pokemonDetail.name?.replaceFirstChar { it.uppercase() }
        Glide.with(requireContext())
            .load(pokemonDetail.getImageUrl())
            .centerCrop()
            .into(binding.imagePokemon)
        pokemonDetail.abilities?.forEach {
            println("ability: ${it.ability?.name}")
        }
    }

    override fun onDestroyView() {
        viewModel.pokemonDetail.value = null
        super.onDestroyView()
        _binding = null
    }
}