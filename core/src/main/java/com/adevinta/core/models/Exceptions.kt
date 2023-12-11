package com.adevinta.core.models

class NoNetworkException(message: String) : Throwable("No network Exception - $message")
class HttpCodeException(code: Int, message: String) :
    Throwable("Network exception code $code - $message")

class ResponseException(code: Int, message: String) :
    Throwable("Response exception code $code - $message")

data class ParsingJsonException(val jsonMessage: String) :
    Throwable("the json can't be parsed : $jsonMessage")

data object NoAlbumsException : Throwable("No album found")