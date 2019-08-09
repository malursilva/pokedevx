package com.github.malursilva.pokedexapp.main

import com.github.malursilva.pokedexapp.shared.events.Events
import com.github.malursilva.pokedexapp.shared.events.RxEventBus

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    override fun onLayoutChange(gridLayoutOption: Boolean) {
        RxEventBus.post(Events.LayoutChange(gridLayoutOption))
    }
}