package com.example.nytcleanarcitecture.domain.base.usecase

import com.example.nytcleanarcitecture.domain.base.coroutines.CoroutineDispatcherProvider
import com.google.common.truth.Truth.assertThat
import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SuspendingInteractorTest {

    private lateinit var testDispatcher: TestCoroutineDispatcher
    private lateinit var coroutinesDispatcherProvider: CoroutineDispatcherProvider

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
    fun `returns result when interactor executed successfully`() = testDispatcher.runBlockingTest {
        assertThat(CalculateSquare(coroutinesDispatcherProvider)(CalculateSquare.Params(3))).isEqualTo(9)
    }

    @Test(expected = IOException::class)
    fun `throws exception when interactor execution failed`() = testDispatcher.runBlockingTest {
        FailingSuspendingInteractor(coroutinesDispatcherProvider)()
    }
}
