package com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList

import com.github.malursilva.pokedexapp.shared.arch.BaseView
import com.github.malursilva.pokedexapp.shared.events.Events
import com.github.malursilva.pokedexapp.shared.model.Pokemon

interface PokemonFavoriteListContract {
    interface View : BaseView<Presenter> {
        fun showFavoritePokemons(favoriteList: List<Pokemon>)
        fun launchPokemonDetailsScreen(pokemonName: String)
        fun updateAdapterList(favoriteList: List<Pokemon>)
        fun changeLayoutManager(layoutOption: Int)
    }

    interface Presenter {
        fun initialize()
        fun update()
        fun onFavoriteOptionSelected(pokemon: Pokemon)
        fun addPokemon(pokemon: Pokemon)
        fun removePokemon(pokemon: Pokemon)
    }
}