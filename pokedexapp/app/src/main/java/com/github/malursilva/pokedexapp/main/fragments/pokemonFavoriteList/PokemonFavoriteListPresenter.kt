package com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList

//import io.objectbox.Box
import com.github.malursilva.pokedexapp.shared.model.Pokemon

class PokemonFavoriteListPresenter(private val view: PokemonFavoriteListContract.View) :
    PokemonFavoriteListContract.Presenter {

    private lateinit var pokemons: ArrayList<Pokemon>

    init {
    }

    override fun initialize() {
        pokemons = ArrayList()
        view.showFavoritePokemons(pokemons)
    }

    override fun update() {
        pokemons.clear()
//        pokemonBox.all.forEach {
//            pokemons.add(Pokemon(it.number, it.name, null, null, null, null, null, null))
//        }
        view.updateAdapterList(pokemons)
    }
}