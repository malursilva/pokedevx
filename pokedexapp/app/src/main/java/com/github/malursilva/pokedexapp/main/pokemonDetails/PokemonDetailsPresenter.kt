package com.github.malursilva.pokedexapp.main.pokemonDetails

import com.github.malursilva.pokedexapp.shared.config.RetrofitConfig
import com.github.malursilva.pokedexapp.shared.events.Events
import com.github.malursilva.pokedexapp.shared.events.RxEventBus
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokemonDetailsPresenter(private val view: PokemonDetailsContract.View) : PokemonDetailsContract.Presenter{
    private val api = RetrofitConfig()

    override fun loadPokemonDetails(pokemon: Pokemon) {
        api.loadPokemonData(pokemon.name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                view.showLoading()
            }
            .doAfterTerminate {
                view.dismissLoading()
            }
            .subscribe({
                it.favorite = pokemon.favorite
                view.showDetailsInfo(it)
            }, {
                view.showErrorTryAgain()
            })
    }

    override fun onFavoriteOptionSelected(pokemon: Pokemon) {
        val event: Any
        pokemon.favorite = !(pokemon.favorite)
        if (pokemon.favorite) {
            event = Events.PokemonFavorited(pokemon)
        }
        else {
            event = Events.PokemonDesfavorited(pokemon)
        }
        RxEventBus.post(event)
    }
}