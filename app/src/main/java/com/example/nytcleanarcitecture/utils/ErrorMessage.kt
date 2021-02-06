package com.example.nytcleanarcitecture.utils

val Throwable.errorMessage: String
    get() = message ?: localizedMessage ?: "An error occurred 😩"
