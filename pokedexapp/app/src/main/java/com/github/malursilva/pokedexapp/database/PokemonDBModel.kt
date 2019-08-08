package com.github.malursilva.pokedexapp.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class PokemonDBModel(
    @PrimaryKey open var id: Int? = 0,
    open var name: String = ""
) : RealmObject() {

}
