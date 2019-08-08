package com.github.malursilva.pokedexapp.shared.events

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.malursilva.pokedexapp.shared.model.Pokemon

class Events {

    class PokemonFavorited {
        private var pokemonFav: Pokemon
        constructor(pokemonFav: Pokemon) {
            this.pokemonFav = pokemonFav
            pokemonFav.favorite = true
        }
        fun getPokemonFavorited(): Pokemon {
            return pokemonFav
        }
    }

    class PokemonDesfavorited {
        private var pokemonDesfav: Pokemon
        constructor(pokemonDesfav: Pokemon) {
            this.pokemonDesfav = pokemonDesfav
            pokemonDesfav.favorite = false
        }
        fun getPokemonDesfavorited(): Pokemon {
            return pokemonDesfav
        }
    }

    class LayoutChange {
        private var layoutOption: Int
        constructor(layoutOption: Int){
            this.layoutOption = layoutOption
        }
        fun getLayoutManager(): Int {
            return layoutOption
        }
    }

}