package com.nosql.authservice.service

interface PasswordService {

    fun convertToHash(password: String): String
}
