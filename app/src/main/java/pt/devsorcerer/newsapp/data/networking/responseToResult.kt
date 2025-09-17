package pt.devsorcerer.newsapp.data.networking

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import pt.devsorcerer.newsapp.domain.util.CallResult
import pt.devsorcerer.newsapp.domain.util.NetworkError


suspend inline fun <reified T> responseToResult (
    response: HttpResponse
): CallResult<T, NetworkError> {
    return when(response.status.value) {
        in 200..299 -> {
            try {
                CallResult.Success(response.body<T>())
            } catch (_: NoTransformationFoundException) {
                CallResult.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> {
            CallResult.Error(NetworkError.REQUEST_TIMEOUT)
        }
        429 -> {
            CallResult.Error(NetworkError.TOO_MANY_REQUESTS)
        }
        in 400..499 -> {
            CallResult.Error(NetworkError.UNKNOWN)
        }
        in 500..599 -> {
            CallResult.Error(NetworkError.SERVER_ERROR)
        }
        else -> {
            CallResult.Error(NetworkError.UNKNOWN)
        }

    }

}