package com.github.malursilva.pokedexapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.malursilva.pokedexapp.R
import com.github.malursilva.pokedexapp.main.adapter.ViewPagerAdapter
import com.github.malursilva.pokedexapp.main.fragments.pokemonFavoriteList.PokemonFavoriteListFragment
import com.github.malursilva.pokedexapp.main.fragments.pokemonList.PokemonListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        presenter = MainPresenter(this)
    }

    override fun setupView() {
        main_toolbar.apply {
            setSupportActionBar(this)
            setNavigationOnClickListener {
                finish()
            }
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setUpBottomNavigation()
    }

    override fun setUpBottomNavigation() {
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            addFragment(PokemonListFragment())
            addFragment(PokemonFavoriteListFragment())
        }
        view_pager.apply {
            offscreenPageLimit = fragmentAdapter.count
            adapter = fragmentAdapter
        }
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_pokemon -> {
                    view_pager.currentItem = 0
                    true
                }
                R.id.navigation_favorites -> {
                    view_pager.currentItem = 1
                    true
                }
                else -> false
            }
        }
    }
}