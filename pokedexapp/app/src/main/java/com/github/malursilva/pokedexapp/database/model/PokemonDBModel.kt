package com.github.malursilva.pokedexapp.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class PokemonDBModel() : RealmObject() {
    @PrimaryKey open var id: Int = 0
    open var number: Int = 0
    open var name: String = ""
//    fun copy(id: Int = this.id, number: Int = this.number, name: String = this.name) {
//    }
}
