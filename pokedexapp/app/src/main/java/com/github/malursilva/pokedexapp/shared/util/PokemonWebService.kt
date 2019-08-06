package com.github.malursilva.pokedexapp.shared.util

import com.github.malursilva.pokedexapp.shared.model.APIResult
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface PokemonWebService {
    @GET("api/v2/pokemon/?limit=151/")
    fun loadPokemons(): Observable<APIResult>

    @GET("api/v2/pokemon/{name}")
    fun loadPokemonDetails(@Path("name") name: String): Observable<Pokemon>
}