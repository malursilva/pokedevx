package com.github.malursilva.pokedexapp.shared.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Pokemon : Serializable {
    var id: Int?
    val name: String
    val sprites: PokemonSprites?
    val types: Array<PokemonType>?
    @SerializedName("base_experience")
    var experience: Int?
    val height : Double?
    val rarity : Int?
    val url: String?
    var favorite: Boolean = false

    constructor(id: Int?, name: String, sprites: PokemonSprites?, types: Array<PokemonType>?,
                experience: Int?, height: Double?, rarity: Int?, url: String?, favorite: Boolean) {
        this.id = id
        this.name = name
        this.sprites = sprites
        this.types = types
        this.experience = experience
        this.height = height
        this.rarity = rarity
        this.url = url
        this.favorite = favorite
    }

    fun showAllTypes(): String? {
        if (types != null) {
            return types.joinToString("/")
        }
        return null
    }
}
