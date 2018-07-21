package com.eatchicken.go.utils

object ErrorCodeCheck {
    fun succeed(error: Int?) = error == 200
}