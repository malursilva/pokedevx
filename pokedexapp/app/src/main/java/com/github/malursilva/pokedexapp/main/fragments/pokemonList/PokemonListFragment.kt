package com.github.malursilva.pokedexapp.main.fragments.pokemonList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.main.adapter.RecyclerAdapter
import com.github.malursilva.pokedexapp.main.pokemonDetails.PokemonDetailsActivity
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_list.view.*

class PokemonListFragment : Fragment(), PokemonListContract.View {
    override lateinit var presenter: PokemonListContract.Presenter
    lateinit var adapter: RecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = PokemonListPresenter(this).apply {
            initialize()
        }
        view.pokemon_list_recycler_view.layoutManager = LinearLayoutManager(context)
    }

    override fun showPokemons(list: List<Pokemon>) {
        adapter = RecyclerAdapter(list)
            .apply {
                onItemClick = { pokemon ->
                    launchPokemonDetailsScreen(pokemon)
                }
                onFavoriteItemClick = { pokemon ->
                    presenter.onFavoriteOptionSelected(pokemon)
                }
            }
        view!!.pokemon_list_recycler_view.adapter = adapter
    }

    override fun updateView(list: List<Pokemon>) {
        adapter.updateList(list)
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        view?.progress_bar?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        view?.progress_bar?.visibility = View.GONE
    }

    override fun showErrorTryAgain() {
        Toast.makeText(context, "Error on loading pokémons/nTry again later", Toast.LENGTH_SHORT).show()
    }

    override fun launchPokemonDetailsScreen(pokemon: Pokemon) {
        val intent = Intent(context, PokemonDetailsActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        startActivity(intent)
    }

    override fun changeLayoutManager(gridLayoutOption: Boolean) {
        if (gridLayoutOption) {
            view!!.pokemon_list_recycler_view.layoutManager = LinearLayoutManager(context)
        } else {
            view!!.pokemon_list_recycler_view.layoutManager = GridLayoutManager(context, 2)
        }
        adapter.changeLayoutOption(gridLayoutOption)
    }

}
