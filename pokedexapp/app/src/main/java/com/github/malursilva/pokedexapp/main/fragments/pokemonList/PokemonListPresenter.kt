package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import com.github.malursilva.pokedexapp.shared.config.RetrofitConfig
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class PokemonListPresenter(private val view: PokemonListContract.View) : PokemonListContract.Presenter {
    private val api = RetrofitConfig()

    override fun initialize() {
            api.loadPokemons()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
               view.showLoading()
            }
            .doAfterTerminate {
                view.dismissLoading()
            }
            .subscribe({
                view.showPokemons(it.results)
            }, {
                view.showErrorTryAgain()
            })
    }
}