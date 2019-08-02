package com.github.malursilva.teste.validator

interface Validator<T> {
    fun validate(model: T): Boolean
}