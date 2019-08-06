package com.github.malursilva.pokedexapp.main.pokemonDetails

import com.github.malursilva.pokedexapp.shared.config.RetrofitConfig
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokemonDetailsPresenter(private val view: PokemonDetailsContract.View) : PokemonDetailsContract.Presenter{
    private val api = RetrofitConfig()

    override fun loadPokemonDetails(name: String) {
        api.loadPokemonData(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.showDetailsInfo(it)
            }, {

            })
    }
}