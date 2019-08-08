package com.github.malursilva.pokedexapp.main

import com.github.malursilva.pokedexapp.shared.arch.BaseView

interface MainContract {
    interface View : BaseView<Presenter> {
        fun setupView()
        fun setUpBottomNavigation()
    }

    interface Presenter {}
}