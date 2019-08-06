package com.github.malursilva.pokedexapp.main

import com.github.malursilva.pokedexapp.shared.arch.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun changeMenuLayout()
        fun showPokemonSelected()
        fun showFavoritePokemonList()
    }

    interface Presenter {

    }
}