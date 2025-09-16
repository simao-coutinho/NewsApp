package pt.devsorcerer.newsapp.domain.util

typealias DomainError = Error

sealed interface CallResult<out D, out E: Error> {
    data class Success<out D>(val data: D): CallResult<D, Nothing>
    data class Error<out E: DomainError>(val error: E): CallResult<Nothing, E>
}

inline fun <T, E: Error, R> CallResult<T, E>.map(map: (T) -> R): CallResult<R, E> {
    return when(this) {
        is CallResult.Error -> CallResult.Error(error)
        is CallResult.Success -> CallResult.Success(map(data))
    }
}

fun <T, E: Error> CallResult<T, E>.asEmptyDataResult(): EmptyResult<E> {
    return map {  }
}

inline fun <T, E: Error> CallResult<T, E>.onSuccess(action: (T) -> Unit): CallResult<T, E> {
    return when(this) {
        is CallResult.Error -> this
        is CallResult.Success -> {
            action(data)
            this
        }
    }
}
inline fun <T, E: Error> CallResult<T, E>.onError(action: (E) -> Unit): CallResult<T, E> {
    return when(this) {
        is CallResult.Error -> {
            action(error)
            this
        }
        is CallResult.Success -> this
    }
}

typealias EmptyResult<E> = CallResult<Unit, E>