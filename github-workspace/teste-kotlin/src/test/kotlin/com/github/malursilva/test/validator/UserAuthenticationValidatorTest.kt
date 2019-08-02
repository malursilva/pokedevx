package com.github.malursilva.test.validator

import com.github.malursilva.teste.model.UserAuthentication
import com.github.malursilva.teste.validator.UserAuthenticationValidator
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class UserAuthenticationValidatorTest {
    private lateinit var userAuthenticationValidator: UserAuthenticationValidator

    @Before
    fun setUp() {
        userAuthenticationValidator = UserAuthenticationValidator()
    }

    @Test
    fun shouldBeValid() {
        // given
        val userAuthentication = UserAuthentication("anyUsername", "anyPassword")

        // then
        assertTrue(userAuthenticationValidator.validate(userAuthentication))
    }

    @Test
    fun shouldBeInvalidWhenUsernameStartsWithNumber() {
        // given
        val userAuthentication = UserAuthentication("1anyUsername", "anyPassword")

        // then
        assertFalse(userAuthenticationValidator.validate(userAuthentication))
    }

    @Test
    fun shouldBeInvalidWhenUsernameSizeIsSmallerThanMin() {
        // given
        val userAuthentication = UserAuthentication("user", "anyPassword")

        // then
        assertFalse(userAuthenticationValidator.validate(userAuthentication))
    }

    @Test
    fun shouldBeInvalidWhenPasswordSizeIsSmallerThanMin() {
        // given
        val userAuthentication = UserAuthentication("anyUsername", "pass")

        // then
        assertFalse(userAuthenticationValidator.validate(userAuthentication))
    }

    @Test
    fun shouldBeInvalidWhenPasswordSizeIsLargerThanMax() {
        // given
        val userAuthentication = UserAuthentication("anyUsername", "anyPasswordBiggerThanTwentyCharacters")

        // then
        assertFalse(userAuthenticationValidator.validate(userAuthentication))
    }
}