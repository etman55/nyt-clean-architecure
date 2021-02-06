package com.example.domain.base.usecase

import com.example.domain.base.coroutines.CoroutineDispatcherProvider
import kotlinx.coroutines.withContext

/**
 * An interactor (use case in Clean Architecture) represents an execution unit of asynchronous work.
 * A [SuspendingInteractor] returns a single response through a suspend function.
 *
 * Work will be executed on thread as specified by the [dispatcher] of the interactor.
 */
abstract class SuspendingInteractor<in Params, out R>(private val dispatcher: CoroutineDispatcherProvider) {

    /**
     * Define the work to be performed by this interactor.
     */
    protected abstract suspend fun execute(params: Params? = null): R

    /**
     * Execute the the interactor.
     */
    public suspend operator fun invoke(params: Params? = null): R = withContext(context = dispatcher.io) {
        this@SuspendingInteractor.execute(params)
    }
}
