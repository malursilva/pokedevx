package com.github.malursilva.pokedexapp.shared.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PokemonSprites : Serializable {
    @SerializedName("front_default")
    val frontDefault: String

    constructor(frontDefault: String) {
        this.frontDefault = frontDefault
    }
}