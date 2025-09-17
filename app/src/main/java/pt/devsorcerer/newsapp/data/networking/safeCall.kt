package pt.devsorcerer.newsapp.data.networking


import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import pt.devsorcerer.newsapp.domain.util.CallResult
import pt.devsorcerer.newsapp.domain.util.NetworkError
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): CallResult<T, NetworkError> {
    val result = try {
        execute()
    } catch (_: UnresolvedAddressException) {
        return CallResult.Error(NetworkError.NO_INTERNET)
    } catch (_: SerializationException) {
        return CallResult.Error(NetworkError.SERIALIZATION)
    } catch (_: Exception) {
        coroutineContext.ensureActive()
        return CallResult.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(result)
}