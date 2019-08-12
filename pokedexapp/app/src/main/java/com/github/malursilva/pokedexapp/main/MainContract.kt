package com.github.malursilva.pokedexapp.main

import com.github.malursilva.pokedexapp.shared.arch.BaseView

interface MainContract {
    interface View : BaseView<Presenter> {
        fun setupView()
        fun setUpBottomNavigation()
        fun onLayoutChange(view: android.view.View)
    }

    interface Presenter {
        fun onLayoutChange(layoutOption: Int)
    }
}