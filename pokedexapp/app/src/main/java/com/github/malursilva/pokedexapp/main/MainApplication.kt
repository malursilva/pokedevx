package com.github.malursilva.pokedexapp.main

import android.app.Application
import android.content.Context
import com.github.malursilva.pokedexapp.R
import io.realm.Realm
import io.realm.RealmConfiguration

class MainApplication : Application() {
    companion object {
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext

        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .name(getString(R.string.db_filename))
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}