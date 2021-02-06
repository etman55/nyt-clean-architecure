package com.example.nytcleanarcitecture.base

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.nytcleanarcitecture.base.extension.event

open class BaseViewModel<N : Navigation> @ViewModelInject constructor() : ViewModel(), LifecycleObserver {

    protected val navigationEvent = event<N>()

    fun observeNavigation(owner: LifecycleOwner, observer: (N) -> Unit) =
        navigationEvent.observe(owner, observer)
}
