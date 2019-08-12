package com.github.malursilva.pokedexapp.main.pokemonDetails

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.android.synthetic.main.activity_pokemon_details.*
import java.lang.Exception

class PokemonDetailsActivity : AppCompatActivity(), PokemonDetailsContract.View {
    override lateinit var presenter: PokemonDetailsContract.Presenter
    lateinit var pokemon: Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)
        presenter = PokemonDetailsPresenter(this, baseContext)
        setupActionBar()
    }

    override fun onResume() {
        super.onResume()
        val pExtra = intent.getSerializableExtra("pokemon") as Pokemon
        presenter.loadPokemonDetails(pExtra)
    }

    override fun showDetailsInfo(pokemon: Pokemon) {
        this.pokemon = pokemon
        showPokemonImage(pokemon.sprites?.frontDefault)
        pokemon_details_toolbar_name.text = pokemon.name.capitalize()
        pokemon_details_number.text = resources.getString(R.string.pokemon_number_pattern, pokemon.id)
        pokemon_details_type.text = pokemon.showAllTypes()
        pokemon_details_experience.text = pokemon.experience.toString()
        pokemon_details_height.text = resources.getString(R.string.pokemon_height_pattern, pokemon.height)
        showFavoriteIcon(pokemon.favorite)
    }

    override fun showPokemonImage(sprite: String?) {
        Picasso.get()
            .load(sprite)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    assert(pokemon_details_image != null)
                    pokemon_details_image.setImageBitmap(bitmap)
                    if (bitmap != null) {
                        Palette.from(bitmap)
                            .generate(object : Palette.PaletteAsyncListener {
                                override fun onGenerated(palette: Palette?) {
                                    val textSwatch: Palette.Swatch = palette?.dominantSwatch
                                        ?: return
                                    pokemon_details_layout.setBackgroundColor(textSwatch.rgb)
                                    pokemon_details_toolbar_action_back.setColorFilter(textSwatch.bodyTextColor)
                                    pokemon_details_toolbar_name.setTextColor(textSwatch.titleTextColor)
                                    pokemon_details_toolbar_favorite.setColorFilter(textSwatch.bodyTextColor)
                                }
                            })
                    }
                }
                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
            })
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
        pokemon_details_toolbar_action_back.setOnClickListener {
            finish()
        }
        pokemon_details_toolbar_favorite.setOnClickListener {
            presenter.onFavoriteOptionSelected(pokemon)
            showFavoriteIcon(pokemon.favorite)
        }
    }

    override fun showFavoriteIcon(favorite: Boolean) {
        if (favorite) {
            pokemon_details_toolbar_favorite.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            pokemon_details_toolbar_favorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

}