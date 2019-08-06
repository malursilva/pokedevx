package com.github.malursilva.pokedexapp.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.holder_pokemon_card.view.*

class RecyclerAdapter(private val list: List<Pokemon>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    companion object {
        private const val DEFAULT_IMAGE = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/%d.png"
    }
    var onItemClick: ((Pokemon) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_pokemon_list, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }

        fun bind(pokemon: Pokemon) = with(itemView) {
            pokemon_name.text = pokemon.name.capitalize()
            pokemon_number.text = (adapterPosition+1).toString()
            Picasso.get().load(String.format(DEFAULT_IMAGE,adapterPosition+1)).into(pokemon_image)
        }
    }
}