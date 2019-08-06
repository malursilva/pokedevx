package com.github.malursilva.pokedexapp.shared.model

import java.io.Serializable


class PokemonType(val types: List<Type>, val url: String) : Serializable {
    fun showAllTypes(): String {
        return types.joinToString("/")
    }
}
