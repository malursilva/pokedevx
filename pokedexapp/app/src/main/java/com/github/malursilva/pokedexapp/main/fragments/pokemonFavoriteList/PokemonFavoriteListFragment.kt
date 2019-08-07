package com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.main.adapter.RecyclerAdapter
import com.github.malursilva.pokedexapp.main.pokemonDetails.PokemonDetailsActivity
import com.github.malursilva.pokedexapp.shared.events.GlobalBus
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_favorite_list.view.*

class PokemonFavoriteListFragment : Fragment(), PokemonFavoriteListContract.View {
    override lateinit var presenter: PokemonFavoriteListContract.Presenter
    lateinit var adapter: RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_favorite_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PokemonFavoriteListPresenter(this).apply {
            initialize()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.update()
    }

    override fun showFavoritePokemons(favoriteList: List<Pokemon>) {
        adapter = RecyclerAdapter(favoriteList)
        adapter.onItemClick = { pokemon ->
            launchPokemonDetailsScreen(pokemon.name)
        }
        view!!.pokemon_favorite_list_recycler_view.adapter = adapter
    }

    override fun updateAdapterList(favoriteList: List<Pokemon>) {
        adapter.updateList(favoriteList)
    }

    override fun launchPokemonDetailsScreen(pokemonName: String) {
        val intent = Intent(context, PokemonDetailsActivity::class.java)
        intent.putExtra("pokemon", pokemonName)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalBus.getBus().unregister(this)
    }

}