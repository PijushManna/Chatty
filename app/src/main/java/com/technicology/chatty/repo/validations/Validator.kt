package com.technicology.chatty.repo.validations

import android.util.Patterns

object Validator {
    fun isEmailValid(email: String): Boolean{
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(pass: String): Boolean{
        return isLenValid(pass) && containsUpperCase(pass) && containsLowerCase(pass) && containsDigits(pass) && containsSymbol(pass)
    }

    fun isLenValid(pass: String) = pass.length > 6
    fun containsUpperCase(pass: String) =  pass.any { it.isUpperCase() }
    fun containsLowerCase(pass: String) =  pass.any{ it.isLowerCase() }
    fun containsDigits(pass: String) =  pass.any{ it.isDigit() }
    fun containsSymbol(pass: String) =  pass.any{ !it.isLetterOrDigit() }
}