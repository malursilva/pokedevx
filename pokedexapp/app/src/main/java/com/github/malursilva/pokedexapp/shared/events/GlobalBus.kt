package com.github.malursilva.pokedexapp.shared.events
import org.greenrobot.eventbus.EventBus

object GlobalBus {
    val sBus: EventBus? = null

    fun getBus(): EventBus {
        if(sBus == null) {
            return EventBus.getDefault()
        }
        return sBus
    }
}