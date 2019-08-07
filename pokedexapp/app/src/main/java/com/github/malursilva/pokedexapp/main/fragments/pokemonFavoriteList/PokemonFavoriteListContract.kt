package com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList

import com.github.malursilva.pokedexapp.shared.arch.BaseView
import com.github.malursilva.pokedexapp.shared.events.Events
import com.github.malursilva.pokedexapp.shared.model.Pokemon

interface PokemonFavoriteListContract {
    interface View : BaseView<Presenter> {
        fun showFavoritePokemons(favoriteList: List<Pokemon>)
        fun launchPokemonDetailsScreen(pokemonName: String)
        fun updateAdapterList(favoriteList: List<Pokemon>)
    }

    interface Presenter {
        fun initialize()
        fun update()
//        fun getPokemonAdded(presenterEventMessage: Events.ListPToFavoriteP)
    }
}