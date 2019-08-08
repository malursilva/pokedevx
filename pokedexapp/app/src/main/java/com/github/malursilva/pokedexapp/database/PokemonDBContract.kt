package com.github.malursilva.pokedexapp.database

import io.realm.Realm
import io.realm.RealmResults

interface PokemonDBContract {

    interface Presenter {
        fun addPokemonDB(realm: Realm, pokemon: PokemonDBModel): Boolean
        fun removePokemonDB(realm: Realm, id: Int?): Boolean
        fun listPokemons(realm: Realm): RealmResults<PokemonDBModel>
    }
}