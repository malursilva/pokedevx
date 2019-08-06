package com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.main.adapter.RecyclerAdapter
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.github.malursilva.pokedexapp.shared.model.PokemonSprites
import kotlinx.android.synthetic.main.fragment_pokemon_favorite_list.view.*

class PokemonFavoriteListFragment : Fragment() {
    private val favList = mutableListOf<Pokemon>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_favorite_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecyclerAdapter(favList)
        adapter.onItemClick = { pokemon ->
            Log.d("TAG", "Clicou em " + pokemon.name)
            // chamar a activity de details aqui
        }
        view.pokemon_favorite_list_recycler_view.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}