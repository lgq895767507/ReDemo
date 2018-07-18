package com.eatchicken.go.exception

class ApiException(private val code: Int, message: String) : Exception(message) {

    override fun toString(): String {
        return "ApiException{" +
                "code='" + code + '\''.toString() +
                ", msg='" + message + '\''.toString() +
                '}'.toString()
    }
}
