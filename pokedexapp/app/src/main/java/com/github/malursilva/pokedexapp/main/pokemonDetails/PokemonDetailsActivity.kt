package com.github.malursilva.pokedexapp.main.pokemonDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_details.*

class PokemonDetailsActivity : AppCompatActivity(), PokemonDetailsContract.View {
    override lateinit var presenter: PokemonDetailsContract.Presenter
    lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)
        presenter = PokemonDetailsPresenter(this)
        setupActionBar()
    }

    override fun onResume() {
        super.onResume()
        val pExtra = intent.getSerializableExtra("pokemon") as Pokemon
        presenter.loadPokemonDetails(pExtra)
    }

    override fun showDetailsInfo(pokemon: Pokemon) {
        this.pokemon = pokemon
        Picasso.get().load(pokemon.sprites?.frontDefault).into(pokemon_details_image)
        pokemon_details_name.text = pokemon.name.capitalize()
        pokemon_details_number.text = resources.getString(R.string.pokemon_number_pattern, pokemon.id)
        pokemon_details_type.text = pokemon.showAllTypes()
        pokemon_details_experience.text = pokemon.experience.toString()
        pokemon_details_height.text = resources.getString(R.string.pokemon_height_pattern, pokemon.height)
        pokemon_details_rarity.text = pokemon.rarity.toString()
        showFavoriteIcon(pokemon.favorite)
    }

    override fun showLoading() {
        details_progress_bar?.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        details_progress_bar?.visibility = View.GONE
    }

    override fun showErrorTryAgain() {
        Toast.makeText(this, "Error on loading pokémons/nTry again later", Toast.LENGTH_SHORT).show()
    }

    override fun setupActionBar() {
        actionBar?.setDisplayShowHomeEnabled(false)
        action_back_details_toolbar.setOnClickListener {
            finish()
        }
        pokemon_details_favorite.setOnClickListener {
            presenter.onFavoriteOptionSelected(pokemon)
            showFavoriteIcon(pokemon.favorite)
        }
    }

    override fun showFavoriteIcon(favorite: Boolean) {
        if (favorite) {
            pokemon_details_favorite.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            pokemon_details_favorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

}