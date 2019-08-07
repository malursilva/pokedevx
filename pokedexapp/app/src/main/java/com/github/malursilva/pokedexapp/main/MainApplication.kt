package com.github.malursilva.pokedexapp.main

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .name("favPokemonsDB")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(realmConfig)

    }

}