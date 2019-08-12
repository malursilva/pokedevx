package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.main.adapter.PokemonRecyclerAdapter
import com.github.malursilva.pokedexapp.main.pokemonDetails.PokemonDetailsActivity
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.android.synthetic.main.fragment_pokemon_list.view.*

class PokemonListFragment : Fragment(), PokemonListContract.View {
    companion object {
        private const val LIST_VIEW_TYPE = 1
        private const val GRID_VIEW_TYPE = 2
    }
    override lateinit var presenter: PokemonListContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PokemonListPresenter(this, context).apply {
            initialize()
        }
        pokemon_list_recycler_view.layoutManager = GridLayoutManager(context, 1)
    }

    override fun showPokemons(list: List<Pokemon>) {
        pokemon_list_recycler_view.apply {
            adapter = PokemonRecyclerAdapter(list,
                onItemClick = { pokemon -> launchPokemonDetailsScreen(pokemon) },
                onFavoriteItemClick = { pokemon -> presenter.onFavoriteOptionSelected(pokemon) })
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun updateView(pokemon: Pokemon) {
        pokemon_list_recycler_view.apply {
            (adapter as PokemonRecyclerAdapter).apply {
                updatePosition(pokemon)
                notifyDataSetChanged()
            }
        }
    }

    override fun showLoading() {
        view?.progress_bar?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        view?.progress_bar?.visibility = View.GONE
    }

    override fun showErrorTryAgain() {
        Toast.makeText(context, "Error on loading pokémons\nTry again later", Toast.LENGTH_SHORT).show()
    }

    override fun launchPokemonDetailsScreen(pokemon: Pokemon) {
        val intent = Intent(context, PokemonDetailsActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }

    override fun changeLayoutManager(layoutOption: Int) {
        pokemon_list_recycler_view.apply {
            (layoutManager as GridLayoutManager).spanCount = when (layoutOption) {
                LIST_VIEW_TYPE -> {
                    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
                    1
                }
                GRID_VIEW_TYPE -> {
                    removeItemDecoration(getItemDecorationAt(0))
                    2
                }
                else -> throw Exception("Layout error")
            }
            (adapter as PokemonRecyclerAdapter).changeLayoutOption(layoutOption)
        }
    }
}
