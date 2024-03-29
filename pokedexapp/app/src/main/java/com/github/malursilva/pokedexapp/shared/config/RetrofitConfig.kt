package com.github.malursilva.pokedexapp.shared.config

import android.content.Context
import com.github.malursilva.pokedexapp.shared.model.APIResult
import com.github.malursilva.pokedexapp.shared.model.Pokemon
import com.github.malursilva.pokedexapp.shared.util.PokemonWebService
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import java.util.concurrent.TimeUnit

class RetrofitConfig (val context: Context?){
    private val retrofit: Retrofit
    private val service: PokemonWebService

    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(ChuckInterceptor(context))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder().setLenient().create()

        this.retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create<PokemonWebService>(PokemonWebService::class.java)
    }

    fun loadPokemons(): Observable<APIResult> {
        return service.loadPokemons()
    }

    fun loadPokemonData(name: String): Observable<Pokemon> {
        return service.loadPokemonDetails(name)
    }
}