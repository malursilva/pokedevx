package com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.main.adapter.RecyclerAdapter
import com.github.malursilva.pokedexapp.main.pokemonDetails.PokemonDetailsActivity
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_favorite_list.*

class PokemonFavoriteListFragment : Fragment(), PokemonFavoriteListContract.View {
    companion object {
        private const val LIST_VIEW_TYPE = 1
        private const val GRID_VIEW_TYPE = 2
    }

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
        pokemon_favorite_list_recycler_view.layoutManager = GridLayoutManager(context, 1)
    }

    override fun onResume() {
        super.onResume()
        presenter.update()
    }

    override fun showFavoritePokemons(favoriteList: List<Pokemon>) {
        pokemon_favorite_list_recycler_view.apply {
            adapter = RecyclerAdapter(favoriteList,
                onItemClick = { pokemon -> launchPokemonDetailsScreen(pokemon) },
                onFavoriteItemClick = { pokemon -> presenter.onFavoriteOptionSelected(pokemon) })
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun updateAdapterList(favoriteList: List<Pokemon>) {
        pokemon_favorite_list_recycler_view.apply {
            (adapter as RecyclerAdapter).apply {
                updateList(favoriteList)
                notifyDataSetChanged()
            }
        }
    }

    override fun launchPokemonDetailsScreen(pokemon: Pokemon) {
        val intent = Intent(context, PokemonDetailsActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }

    override fun changeLayoutManager(layoutOption: Int) {
        pokemon_favorite_list_recycler_view.apply {
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
            (adapter as RecyclerAdapter).changeLayoutOption(layoutOption)
        }
    }
}