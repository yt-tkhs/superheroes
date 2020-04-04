package app.ytak.superheroes.core

sealed class AsyncState<out T : Any> {

    object Ready : AsyncState<Nothing>()

    object Loading : AsyncState<Nothing>()

    data class Success<T : Any>(val data: T) : AsyncState<T>()

    data class Failure<T : Any>(val error: ErrorEvent) : AsyncState<T>()
}
