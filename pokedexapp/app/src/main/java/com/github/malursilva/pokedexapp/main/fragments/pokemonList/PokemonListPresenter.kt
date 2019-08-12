package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import android.content.Context
import android.util.Log
import com.github.malursilva.pokedexapp.shared.config.RetrofitConfig
import com.github.malursilva.pokedexapp.shared.events.Events
import com.github.malursilva.pokedexapp.shared.events.RxEventBus
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.github.malursilva.pokedexapp.shared.model.PokemonColor
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokemonListPresenter(
    private val view: PokemonListContract.View,
    context: Context?
) : PokemonListContract.Presenter {
    private var list: ArrayList<Pokemon> = ArrayList()
    private var listFavs: ArrayList<Pokemon> = ArrayList()
    private val api = RetrofitConfig(context)
    var favoriteLoaded: Observable<Events.PokemonFavoriteLoaded> = RxEventBus.subscribe()
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
                loadFavoritesFromDB()
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
        } else {
            event = Events.PokemonDesfavorited(pokemon)
        }
        RxEventBus.post(event)
    }

    override fun initializeObservers() {
        favoriteLoaded
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Observable.empty())
            .subscribe {
                listFavs.add(it.getPokemonFavoriteLoaded())
            }
        favoritedObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Observable.empty())
            .subscribe {
                updateList(it.getPokemonFavorited())
            }
        desfavoritedObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Observable.empty())
            .subscribe {
                updateList(it.getPokemonDesfavorited())
            }
        layoutChangeObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorResumeNext(Observable.empty())
            .subscribe {
                view.changeLayoutManager(it.getLayoutManager())
            }
    }

    override fun updateList(pokemon: Pokemon) {
        list[(pokemon.id - 1)] = pokemon
        view.updateView(list)
    }

    override fun loadFavoritesFromDB() {
        listFavs.forEach {
            list[it.id - 1] = it
        }
    }
}
