package com.example.vknewsclient.extensions

fun String.isValidApiKey(): Boolean {
    return Regex("^[0-9a-z]{32}").matches(this)
}