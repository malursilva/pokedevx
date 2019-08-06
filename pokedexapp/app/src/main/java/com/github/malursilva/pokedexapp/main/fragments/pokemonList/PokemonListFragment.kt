package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.main.adapter.RecyclerAdapter
import com.github.malursilva.pokedexapp.main.pokemonDetails.PokemonDetailsActivity
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_list.view.*

class PokemonListFragment : Fragment(), PokemonListContract.View {
    override lateinit var presenter: PokemonListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PokemonListPresenter(this).apply {
            initialize()
        }
    }

    override fun showPokemons(list: List<Pokemon>) {
        val adapter = RecyclerAdapter(list)
        adapter.onItemClick = { pokemon ->
            launchPokemonDetailsScreen(pokemon.name)
        }
        view!!.pokemon_list_recycler_view.adapter = adapter
    }

    override fun showLoading() {
        view?.progress_bar?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        view?.progress_bar?.visibility = View.GONE
    }

    override fun showErrorTryAgain() {
    }

    override fun launchPokemonDetailsScreen(pokemonName: String) {
        val intent = Intent(context, PokemonDetailsActivity::class.java)
        intent.putExtra("pokemon", pokemonName)
        startActivity(intent)
    }
}
