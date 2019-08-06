package com.github.malursilva.pokedexapp.main.pokemonDetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pokemon_details.*

class PokemonDetailsActivity : AppCompatActivity(), PokemonDetailsContract.View {
    override lateinit var presenter: PokemonDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)
        presenter = PokemonDetailsPresenter(this)
        setupActionBar()
    }

    override fun onResume() {
        super.onResume()
        val pExtra = intent.getSerializableExtra("pokemon") as String
        presenter.loadPokemonDetails(pExtra)
    }

    override fun showDetailsInfo(pokemon: Pokemon) {
        Picasso.get().load(pokemon.sprites?.frontDefault).into(pokemon_details_image)
        pokemon_details_name.text = pokemon.name.capitalize()
        pokemon_details_number.text = pokemon.id.toString()
       // pokemon_details_type.text = pokemon.types.toString()
        pokemon_details_experience.text = pokemon.experience.toString()
        pokemon_details_height.text = pokemon.height.toString() + " m"
        pokemon_details_rarity.text = pokemon.rarity.toString()
    }

    override fun setupActionBar() {
        actionBar?.setDisplayShowHomeEnabled(false)
        action_back_details_toolbar.setOnClickListener {
            finish()
        }
    }

}