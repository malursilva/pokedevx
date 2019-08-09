package com.github.malursilva.pokedexapp.shared.model

import com.google.gson.annotations.SerializedName

class PokemonColor {
    val name: String
    @SerializedName("pokemon_species")
    val species: ArrayList<Specie>

    constructor(name: String, species: ArrayList<Specie>) {
        this.name = name
        this.species = species
    }

    inner class Specie (val name: String, val url: String)
}