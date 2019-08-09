package com.github.malursilva.pokedexapp.shared.events

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
        private var gridLayoutOption: Boolean
        constructor(layoutOption: Boolean){
            this.gridLayoutOption = layoutOption
        }
        fun getLayoutManager(): Boolean {
            return gridLayoutOption
        }
    }

    class PokemonFavoriteLoaded {
        private var pokemonFavorite: Pokemon
        constructor(pokemonFavorite: Pokemon) {
            this.pokemonFavorite = pokemonFavorite
        }
        fun getPokemonFavoriteLoaded(): Pokemon {
            return pokemonFavorite
        }
    }
}