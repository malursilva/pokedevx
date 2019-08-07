package com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList

//import io.objectbox.Box
import com.github.malursilva.pokedexapp.shared.model.Pokemon

class PokemonFavoriteListPresenter(private val view: PokemonFavoriteListContract.View) :
    PokemonFavoriteListContract.Presenter {

//    private var pokemonBox: Box<PokemonDBModel>
    private lateinit var pokemons: ArrayList<Pokemon>

    init {
//        pokemonBox = ObjectBox.boxStore.boxFor(PokemonDBModel::class.java)
//        pokemonBox.put(PokemonDBModel(0, 25, "pikachu"))
//        pokemonBox.put(PokemonDBModel(0, 151, "mew"))
//        pokemonBox.removeAll()
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

//    @Subscribe
//    override fun getPokemonAdded(presenterEventMessage: Events.ListPToFavoriteP) {
//        val pokemon = presenterEventMessage.getMessage()
//        pokemonBox.put(PokemonDBModel(0, pokemon.name))
//    }

}