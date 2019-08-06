package com.github.malursilva.pokedexapp.shared.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Pokemon : Serializable {
    val id: Int?
    val name: String
    val sprites: PokemonSprites?
    val types: Array<PokemonType>
    @SerializedName("base_experience")
    val experience: Int?
    val height : Double?
    val rarity : Int?
    val url: String

    constructor(id: Int, name: String, sprites: PokemonSprites, types: Array<PokemonType>,
                experience: Int, height: Double, rarity: Int, url: String) {
        this.id = id
        this.name = name
        this.sprites = sprites
        this.types = types
        this.experience = experience
        this.height = height
        this.rarity = rarity
        this.url = url
    }
}
