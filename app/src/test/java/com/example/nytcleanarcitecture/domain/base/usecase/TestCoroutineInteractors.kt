package com.example.nytcleanarcitecture.domain.base.usecase

import com.example.nytcleanarcitecture.domain.base.coroutines.CoroutineDispatcherProvider
import com.example.nytcleanarcitecture.domain.base.exception.requireParams
import com.example.nytcleanarcitecture.domain.fake.FakeMostPopularArticlesRepository.Companion.ERROR_MSG
import java.io.IOException
import java.net.SocketTimeoutException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.function.ThrowingRunnable

class CalculateSquare(
    dispatcher: CoroutineDispatcherProvider
) : SuspendingInteractor<CalculateSquare.Params, Int>(dispatcher) {

    override suspend fun execute(params: Params?): Int {
        requireNotNull(params)
        delay(1000L)
        return params.value * params.value
    }

    class Params(val value: Int)
}

class FailingSuspendingInteractor(
    dispatcher: CoroutineDispatcherProvider
) : SuspendingInteractor<Nothing, Unit>(dispatcher) {
    override suspend fun execute(params: Nothing?) {
        delay(1000L)
        throw IOException()
    }
}

class FlowInteractorWithThreeEmissions(
    dispatcher: CoroutineDispatcherProvider
) : FlowInteractor<Nothing, Int>(dispatcher) {
    override fun execute(params: Nothing?): Flow<Int> {
        return flow {
            delay(1000L)
            repeat(3) {
                emit(it)
            }
        }
    }
}

class FlowInteractorWithException(
    dispatcher: CoroutineDispatcherProvider
) : FlowInteractor<Nothing, Int>(dispatcher) {

    override fun execute(params: Nothing?): Flow<Int> {
        return flow {
            delay(1000L)
            throw IOException()
        }
    }
}

class FlowInteractorWithSocketException(
    dispatcher: CoroutineDispatcherProvider
) : FlowInteractor<Nothing, Int>(dispatcher) {

    override fun execute(params: Nothing?): Flow<Int> {
        return flow {
            throw SocketTimeoutException(ERROR_MSG)
        }
    }
}

class FlowInteractorWithParams(
    dispatcher: CoroutineDispatcherProvider
) : FlowInteractor<String, String>(dispatcher) {

    override fun execute(params: String?): Flow<String> {
        requireParams(params)
        return flow {
            delay(1000L)
            emit(params)
        }
    }
}

@ExperimentalCoroutinesApi
inline fun <reified T : Throwable> TestCoroutineScope.assertThrows(
    crossinline runnable: suspend () -> Unit
): T {
    val throwingRunnable = ThrowingRunnable {
        val job: Deferred<Unit> = async { runnable() }
        job.getCompletionExceptionOrNull()?.run { throw this }
        job.cancel()
    }
    return Assert.assertThrows(T::class.java, throwingRunnable)
}
