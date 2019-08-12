package com.github.malursilva.pokedexapp.main.pokemonDetails

import com.github.malursilva.pokedexapp.shared.arch.BaseView
import com.github.malursilva.pokedexapp.shared.model.Pokemon

interface PokemonDetailsContract {
    interface View: BaseView<Presenter> {
        fun showDetailsInfo(pokemon: Pokemon)
        fun setupActionBar()
        fun showLoading()
        fun dismissLoading()
        fun showErrorTryAgain()
        fun showFavoriteIcon(favorite: Boolean)
        fun showPokemonImage(sprite: String?)
    }

    interface Presenter {
        fun loadPokemonDetails(pokemon: Pokemon)
        fun onFavoriteOptionSelected(pokemon: Pokemon)
    }

}