package com.example.nytcleanarcitecture.domain.base.usecase

import com.example.nytcleanarcitecture.domain.base.coroutines.CoroutineDispatcherProvider
import com.example.nytcleanarcitecture.domain.fake.FakeMostPopularArticlesRepository.Companion.ERROR_MSG
import com.google.common.truth.Truth.assertThat
import java.io.IOException
import java.net.SocketTimeoutException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FlowInteractorTest {

    private lateinit var coroutinesDispatcherProvider: CoroutineDispatcherProvider
    private lateinit var testDispatcher: TestCoroutineDispatcher

    @Before
    fun setUp() {
        testDispatcher = TestCoroutineDispatcher()
        coroutinesDispatcherProvider = CoroutineDispatcherProvider(
            io = testDispatcher,
            computation = testDispatcher,
            ui = testDispatcher
        )
    }

    @Test
    fun `emits each value when flow interactor produces multiple values`() =
        testDispatcher.runBlockingTest {

            val results = FlowInteractorWithThreeEmissions(coroutinesDispatcherProvider)
                .invoke()
                .toList()
            assertThat(results).isEqualTo(listOf(0, 1, 2))
        }

    @Test(expected = IOException::class)
    fun `catches exception thrown by flow interactor`() = testDispatcher.runBlockingTest {
        FlowInteractorWithException(coroutinesDispatcherProvider)
            .invoke()
            .collect()
    }

    @Test
    fun `catches network exception thrown by flow interactor`() = testDispatcher.runBlockingTest {
        val exception: SocketTimeoutException = assertThrows {
            FlowInteractorWithSocketException(coroutinesDispatcherProvider)().collect()
        }
        assertThat(exception)
            .hasMessageThat()
            .isEqualTo(ERROR_MSG)
    }

    @Test
    fun `check that ParamsUseCase returns correct data`() = testDispatcher.runBlockingTest {
        val param = "Correct data"
        val useCase = FlowInteractorWithParams(coroutinesDispatcherProvider)
        val result: String = useCase(param).first()
        assertThat(result).isEqualTo(param)
    }
}
