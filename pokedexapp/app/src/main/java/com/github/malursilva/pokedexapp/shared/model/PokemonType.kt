package com.github.malursilva.pokedexapp.shared.model

import java.io.Serializable


class PokemonType(val type: Type) : Serializable {
    override fun toString(): String {
        return type.name.capitalize()
    }

    inner class Type (val name: String, val url: String) : Serializable
}
