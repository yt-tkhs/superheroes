package app.ytak.superheroes.util

sealed class AsyncListState<out T : Any> {

    object Ready : AsyncListState<Nothing>()

    data class Loading<T : Any>(val data: List<T>) : AsyncListState<T>()

    data class Success<T : Any>(val data: List<T>) : AsyncListState<T>()

    data class Failure<T : Any>(val data: List<T>, val error: ErrorEvent) : AsyncListState<T>()
}

fun <T : Any> AsyncListState<T>.toLoading(): AsyncListState.Loading<T> =
    when (this) {
        is AsyncListState.Ready -> AsyncListState.Loading(emptyList())
        is AsyncListState.Loading -> this
        is AsyncListState.Success -> AsyncListState.Loading(data)
        is AsyncListState.Failure -> AsyncListState.Loading(data)
    }

fun <T : Any> AsyncListState<T>.toSuccess(data: List<T>): AsyncListState.Success<T> =
    when (this) {
        is AsyncListState.Ready -> AsyncListState.Success(data)
        is AsyncListState.Loading -> AsyncListState.Success(this.data + data)
        is AsyncListState.Success -> AsyncListState.Success(this.data + data)
        is AsyncListState.Failure -> AsyncListState.Success(this.data + data)
    }

fun <T : Any> AsyncListState<T>.toFailure(error: ErrorEvent): AsyncListState.Failure<T> =
    when (this) {
        is AsyncListState.Ready -> AsyncListState.Failure(emptyList(), error)
        is AsyncListState.Loading -> AsyncListState.Failure(data, error)
        is AsyncListState.Success -> AsyncListState.Failure(data, error)
        is AsyncListState.Failure -> AsyncListState.Failure(data, error)
    }
