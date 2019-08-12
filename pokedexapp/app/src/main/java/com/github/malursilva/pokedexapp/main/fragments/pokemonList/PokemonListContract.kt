package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import com.github.malursilva.pokedexapp.shared.arch.BaseView
import com.github.malursilva.pokedexapp.shared.model.Pokemon

interface PokemonListContract {
    interface View : BaseView<Presenter> {
        fun showPokemons(list: List<Pokemon>)
        fun showLoading()
        fun dismissLoading()
        fun updateView(pokemon: Pokemon)
        fun showErrorTryAgain()
        fun launchPokemonDetailsScreen(pokemon: Pokemon)
        fun changeLayoutManager(layoutOption: Int)
    }

    interface Presenter {
        fun initialize()
        fun initializeObservers()
        fun onFavoriteOptionSelected(pokemon: Pokemon)
        fun updateList(pokemon: Pokemon)
        fun loadFavoritesFromDB()
    }
}