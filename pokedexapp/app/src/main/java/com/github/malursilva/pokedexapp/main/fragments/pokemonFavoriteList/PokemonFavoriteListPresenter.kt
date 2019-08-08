package com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList

import com.github.malursilva.pokedexapp.database.PokemonDBModel
import com.github.malursilva.pokedexapp.database.PokemonDBPresenter
import com.github.malursilva.pokedexapp.shared.events.Events
import com.github.malursilva.pokedexapp.shared.events.RxEventBus
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import io.realm.Realm
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class PokemonFavoriteListPresenter(private val view: PokemonFavoriteListContract.View) :
    PokemonFavoriteListContract.Presenter {

    private lateinit var list: ArrayList<Pokemon>
    var favoritedObservable: Observable<Events.PokemonFavorited> = RxEventBus.subscribe()
    var desfavoritedObservable: Observable<Events.PokemonDesfavorited> = RxEventBus.subscribe()
    var layoutChangeObservable: Observable<Events.LayoutChange> = RxEventBus.subscribe()
    var pokemonDBAcess: PokemonDBPresenter = PokemonDBPresenter()
    var realm: Realm = Realm.getDefaultInstance()

    override fun initialize() {
        list = ArrayList()
        initializeObservers()
        view.showFavoritePokemons(list)
    }

    override fun update() {
        list.clear()
        val results = pokemonDBAcess.listPokemons(realm)
        results.forEach {
            list.add(Pokemon(it.id, it.name, null, null, null, null, null, null, true))
        }
        view.updateAdapterList(list.sortedWith(compareBy({ it.id })))
    }

    override fun onFavoriteOptionSelected(pokemon: Pokemon) {
        if(pokemon.favorite) {
            RxEventBus.post(Events.PokemonDesfavorited(pokemon))
            removePokemon(pokemon)
        }
    }

    override fun addPokemon(pokemon: Pokemon) {
        pokemonDBAcess.addPokemonDB(realm, PokemonDBModel(pokemon.id, pokemon.name))
        update()
    }

    override fun removePokemon(pokemon: Pokemon) {
        pokemonDBAcess.removePokemonDB(realm, pokemon.id)
        update()
    }

    fun initializeObservers() {
        favoritedObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                addPokemon(it.getPokemonFavorited())
            }
        desfavoritedObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                removePokemon(it.getPokemonDesfavorited())
            }
        layoutChangeObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                view.changeLayoutManager(it.getLayoutManager())
            }
    }
}