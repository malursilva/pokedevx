package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import com.github.malursilva.pokedexapp.shared.config.RetrofitConfig
import com.github.malursilva.pokedexapp.shared.events.Events
import com.github.malursilva.pokedexapp.shared.events.RxEventBus
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokemonListPresenter(private val view: PokemonListContract.View) : PokemonListContract.Presenter {
    private var list: ArrayList<Pokemon> = ArrayList()
    private val api = RetrofitConfig()
    var favoritedObservable: Observable<Events.PokemonFavorited> = RxEventBus.subscribe()
    var desfavoritedObservable: Observable<Events.PokemonDesfavorited> = RxEventBus.subscribe()
    var layoutChangeObservable: Observable<Events.LayoutChange> = RxEventBus.subscribe()

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
                list = it.results as ArrayList<Pokemon>
                view.showPokemons(list)
            }, {
                view.showErrorTryAgain()
            })
        initializeObservers()
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

    override fun initializeObservers() {
        favoritedObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                updateList(it.getPokemonFavorited())
            }
        desfavoritedObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                updateList(it.getPokemonDesfavorited())
            }
        layoutChangeObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.changeLayoutManager(it.getLayoutManager())
            }
    }

    override fun updateList(pokemon: Pokemon) {
        list.set(list.indexOf(pokemon), pokemon)
        view.updateView(list)
    }
}
