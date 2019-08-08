package com.github.malursilva.pokedexapp.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.holder_pokemon_card.view.pokemon_image
import kotlinx.android.synthetic.main.holder_pokemon_card.view.pokemon_name
import kotlinx.android.synthetic.main.holder_pokemon_card.view.pokemon_number
import kotlinx.android.synthetic.main.holder_pokemon_list.view.*

class RecyclerAdapter(private var list: List<Pokemon>)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    companion object {
        private const val DEFAULT_IMAGE =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"
    }

    var onItemClick: ((Pokemon) -> Unit)? = null
    var onFavoriteItemClick: ((Pokemon) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_pokemon_list, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateList(updateList: List<Pokemon>) {
        list = updateList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
            itemView.favorite_icon.setOnClickListener {
                onFavoriteItemClick?.invoke(list[adapterPosition])
            }
        }

        fun bind(pokemon: Pokemon) = with(itemView) {
            pokemon_name.text = pokemon.name.capitalize()
            if(pokemon.id == null) {
                pokemon.id = adapterPosition+1
            }
            pokemon_number.text = resources.getString(R.string.pokemon_number_pattern, pokemon.id)
            Picasso.get().load(String.format(DEFAULT_IMAGE, pokemon.id)).into(pokemon_image)
            itemView.favorite_icon.setImageResource(showFavIcon(pokemon.favorite))
        }

        fun showFavIcon(favorite: Boolean): Int{
            if (favorite) {
                return R.drawable.ic_favorite_filled
            }
            return R.drawable.ic_favorite_border
        }
    }
}