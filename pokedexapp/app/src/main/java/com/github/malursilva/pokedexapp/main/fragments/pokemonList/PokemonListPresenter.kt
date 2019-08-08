package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import com.github.malursilva.pokedexapp.shared.config.RetrofitConfig
import com.github.malursilva.pokedexapp.shared.events.Events
import com.github.malursilva.pokedexapp.shared.events.GlobalBus
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import org.greenrobot.eventbus.Subscribe
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

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

    override fun onFavoriteOptionSelected(pokemon: Pokemon) {
        pokemon.favorite = !(pokemon.favorite)
        //
    }
}
