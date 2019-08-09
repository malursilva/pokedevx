package com.github.malursilva.pokedexapp.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.holder_pokemon_list.view.*

class RecyclerAdapter(private var list: List<Pokemon>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    companion object {
        private const val DEFAULT_IMAGE =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"
        private const val LIST_VIEW_TYPE = 1
        private const val GRID_VIEW_TYPE = 2
    }

    var onItemClick: ((Pokemon) -> Unit)? = null
    var onFavoriteItemClick: ((Pokemon) -> Unit)? = null
    var gridLayoutOption: Boolean = false
    private var currentViewType = LIST_VIEW_TYPE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        if (gridLayoutOption) {
//            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_pokemon_card, parent, false))
//        }
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

    fun changeLayoutOption(gridOptionOn: Boolean) {
        gridLayoutOption = gridOptionOn
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) = with(itemView) {
            setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
            pokemon_name.text = pokemon.name.capitalize()
            if (pokemon.id == 0) {
                pokemon.id = adapterPosition + 1
            }
            pokemon_number.text = resources.getString(R.string.pokemon_number_pattern, pokemon.id)
            Picasso.get().load(String.format(DEFAULT_IMAGE, pokemon.id)).into(pokemon_image)
            favorite_icon.apply {
                setOnClickListener {
                    onFavoriteItemClick?.invoke(list[adapterPosition])
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