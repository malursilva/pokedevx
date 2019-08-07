package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import com.github.malursilva.pokedexapp.shared.arch.BaseView
import com.github.malursilva.pokedexapp.shared.model.Pokemon

interface PokemonListContract {
    interface View : BaseView<Presenter> {
        fun showPokemons(list: List<Pokemon>)
        fun showLoading()
        fun dismissLoading()
        fun showErrorTryAgain()
        fun launchPokemonDetailsScreen(pokemonName: String)
    }
    interface Presenter {
        fun initialize()
        fun onFavoriteOptionSelected(pokemon: Pokemon)
    }
}