package com.github.malursilva.pokedexapp.database

import io.realm.Realm
import io.realm.RealmResults

class PokemonDBPresenter : PokemonDBContract.Presenter {
    override fun addPokemonDB(realm: Realm, pokemon: PokemonDBModel): Boolean {
        try {
            realm.apply {
                beginTransaction()
                copyToRealmOrUpdate(pokemon)
                commitTransaction()
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun removePokemonDB(realm: Realm, id: Int?): Boolean {
        try {
            realm.apply {
                beginTransaction()
                where(PokemonDBModel::class.java).equalTo("id", id).findFirst()?.deleteFromRealm()
                commitTransaction()
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override fun listPokemons(realm: Realm): RealmResults<PokemonDBModel> {
        return realm.where(PokemonDBModel::class.java).findAll()
    }
}