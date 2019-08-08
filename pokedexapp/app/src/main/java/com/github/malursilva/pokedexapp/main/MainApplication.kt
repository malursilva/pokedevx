package com.github.malursilva.pokedexapp.main

import android.app.Application
import android.content.Context
import com.github.malursilva.pokedexapp.shared.events.RxEventBus
import io.realm.Realm
import io.realm.RealmConfiguration


class MainApplication : Application() {
    companion object {
        var ctx: Context? = null
    }
    val bus: RxEventBus = RxEventBus()

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .name("favPokemonsDB")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}