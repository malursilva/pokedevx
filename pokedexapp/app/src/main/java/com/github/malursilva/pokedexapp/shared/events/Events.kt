package com.github.malursilva.pokedexapp.shared.events

import com.github.malursilva.pokedexapp.shared.model.Pokemon

class Events {

    class ListPToFavoriteP (private val pokemon: Pokemon){
        fun getMessage(): Pokemon {
            return pokemon
        }
    }

}