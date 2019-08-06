package com.github.malursilva.pokedexapp.main.pokemonDetails

import com.github.malursilva.pokedexapp.shared.arch.BaseView
import com.github.malursilva.pokedexapp.shared.model.Pokemon

interface PokemonDetailsContract {
    interface View: BaseView<Presenter> {
        fun showDetailsInfo(pokemon: Pokemon)
        fun setupActionBar()
    }

    interface Presenter {
        fun loadPokemonDetails(name: String)
    }

}