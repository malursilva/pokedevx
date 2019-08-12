package com.github.malursilva.pokedexapp.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.holder_pokemon_list.view.*

class PokemonRecyclerAdapter(
    private var list: List<Pokemon>,
    val onItemClick: ((Pokemon) -> Unit)?,
    val onFavoriteItemClick: ((Pokemon) -> Unit)?
) : RecyclerView.Adapter<PokemonRecyclerAdapter.ViewHolder>() {
    companion object {
        private const val DEFAULT_IMAGE =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"
        private const val LIST_VIEW_TYPE = 1
        private const val GRID_VIEW_TYPE = 2
    }
    private var currentViewType = LIST_VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (currentViewType) {
            LIST_VIEW_TYPE -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.holder_pokemon_list,
                    parent,
                    false
                )
            )
            GRID_VIEW_TYPE -> ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.holder_pokemon_card,
                    parent,
                    false
                )
            )
            else -> throw Exception("Invalid view type")
        }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return currentViewType
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updatePosition(pokemon: Pokemon) {
        notifyItemChanged(pokemon.id - 1)
    }

    fun updateList(updateList: List<Pokemon>) {
        list = updateList
        notifyDataSetChanged()
    }

    fun changeLayoutOption(layoutOption: Int) {
        currentViewType = layoutOption
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) = with(itemView) {
            setOnClickListener {
                onItemClick?.invoke(pokemon)
            }
            pokemon_name.text = pokemon.name.capitalize()
            if (pokemon.id == 0) {
                pokemon.id = adapterPosition + 1
            }
            pokemon_number.text = resources.getString(R.string.pokemon_number_pattern, pokemon.id)
            Picasso.get().load(String.format(DEFAULT_IMAGE, pokemon.id)).into(pokemon_image)
            favorite_icon.apply {
                setOnClickListener {
                    onFavoriteItemClick?.invoke(pokemon)
                }
                setImageResource(
                    if (pokemon.favorite) {
                        R.drawable.ic_favorite_filled
                    } else {
                        R.drawable.ic_favorite_border
                    }
                )
            }
        }
    }
}