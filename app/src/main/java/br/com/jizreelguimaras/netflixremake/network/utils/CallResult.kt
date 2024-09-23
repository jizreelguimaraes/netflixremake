package br.com.jizreelguimaras.netflixremake.network.utils


sealed class CallResult<out T> {

    data class Success<out T>(val value: T?, val statusCode: Int?) : CallResult<T>()

    data class Failure(val messageError: String?) : CallResult<Nothing>()
}