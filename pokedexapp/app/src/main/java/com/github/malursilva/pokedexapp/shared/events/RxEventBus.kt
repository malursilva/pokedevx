package com.github.malursilva.pokedexapp.shared.events

import rx.Observable
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject

class RxEventBus {
    companion object {
        val instance: PublishSubject<Any> = PublishSubject.create()

        fun post(event: Any) {
            instance.onNext(event)
        }

        inline fun <reified T> subscribe(): Observable<T> {
            return instance.filter {
                it is T
            }.map {
                it as T
            }
        }

        fun hasObservers(): Boolean {
            return instance.hasObservers()
        }

    }

}