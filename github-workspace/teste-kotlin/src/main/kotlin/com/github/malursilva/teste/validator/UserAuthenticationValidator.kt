package com.github.malursilva.teste.validator

import com.github.malursilva.teste.model.UserAuthentication

class UserAuthenticationValidator : Validator<UserAuthentication> {
    companion object {
        private const val USERNAME_MIN_SIZE = 6
        private const val PASSWORD_MIN_SIZE = 5
        private const val PASSWORD_MAX_SIZE = 20
    }
    override fun validate(model: UserAuthentication): Boolean {
        return model.username.isNotEmpty() && model.username.length >= USERNAME_MIN_SIZE &&
                model.username[0].isLetter() && model.password.isNotEmpty() &&
                model.password.length >= PASSWORD_MIN_SIZE && model.password.length <= PASSWORD_MAX_SIZE
    }
}